import {Injectable} from "@angular/core";
import {NgRedux} from "@angular-redux/store";
import {AppState} from "../../../../shared/store/reducers";
import {FeatureFlagsService, Features} from "../../../../shared/services/featureFlag/feature-flags.service";

@Injectable()
export class DragAndDropService {

  constructor(private store: NgRedux<AppState>) {
  }

  checkFeatureFlag(flagValue):boolean {
    let featureFlag :boolean;
    featureFlag = FeatureFlagsService.getFlagState(flagValue, this.store);
    return featureFlag;
  }

  /***********************************************************************************************
   if the dragged node is a base module instance
   ***********************************************************************************************/
  isBaseModule(serviceInstanceId, from): boolean {
    try {
      let baseModuleFlag = this.store.getState().service.serviceHierarchy[serviceInstanceId].vnfs[from.parent.data.modelName].vfModules[from.data.modelName].properties.baseModule;
      return (baseModuleFlag != 'undefined' ? baseModuleFlag : false);
    }catch(e) {
      return false;
    }
  }


  /***********************************************************************************************
   if the flag is ON and nodes have same parent
   ***********************************************************************************************/
  isAllowDrop(from: any, to: any): boolean {
    return this.checkFeatureFlag(Features.FLAG_1911_INSTANTIATION_ORDER_IN_ASYNC_ALACARTE) && this.isSameParent(from, to);
  }

  private isSameParent(from: any, to: any): boolean {
    try {
      return from.parent.data.trackById === to.parent.parent.data.trackById;
    } catch (e) { //parent not found
      return false;
    }
  }

  /********************************************************************
   * manage drawing-board drag and drop operation
   * @param nodes - array with elements data.
   * @param tree - tree instance
   * @param node - element information
   * @param from - element from information
   * @param to - element to information
   ************************************************************/

  drop(store, instanceId: string, nodes, {from, to}): void {

    if (!this.checkFeatureFlag(Features.FLAG_1911_INSTANTIATION_ORDER_IN_ASYNC_ALACARTE)) return;

    if(this.checkFeatureFlag(Features.FLAG_2008_DISABLE_DRAG_FOR_BASE_MODULE)) {
      if ((to.parent.index == 0 && this.isBaseModule(instanceId, to.parent)) || this.isBaseModule(instanceId, from )) return;
    }

    if (this.isAllowDrop(from, to)) {
      let vfModules = nodes.find((parent) => {
        return parent.trackById === to.parent.parent.data.trackById;
      }).children;
      this.array_move(vfModules, from.index, to.parent.index, instanceId, to.parent.parent.data.vnfStoreKey);
    }

    /*  let firstLevelNames : DragAndDropModel[] = [
        new DragAndDropModel('VF',true),
        new DragAndDropModel('VL',true),
        new DragAndDropModel('VFmodule',false)
      ];

      const fromObject = _.find(firstLevelNames, ['type', from.data.type]);
      const toObject = _.find(firstLevelNames, ['type', to.parent.data.type]);

      /!***********************************************************************************************
       if the type are the same and there in same level + same parent -> then change element position
       ***********************************************************************************************!/
      if(fromObject.isFirstLevel === toObject.isFirstLevel){ // moving element in the same level and in the first level
        if(fromObject.isFirstLevel){
          this.array_move(nodes, from.index , to.parent.index, instanceId);
        } else if(fromObject.isFirstLevel === toObject.isFirstLevel){
          /!* check if they have the same parent *!/
          if(from.parent.data.trackById === to.parent.parent.data.trackById){
            let vfModules = nodes.find((parents)=> {
              return parents.trackById === to.parent.parent.data.trackById;
            }).children;
            this.array_move(vfModules, from.index , to.parent.index, instanceId, to.parent.parent.data.vnfStoreKey);
          }
        }
      }*/
  }


  /********************************************************************
   * move element inside array with elements position
   * @param arr - array with elements data.
   * @param originalPosition - element original position
   * @param destPosition - element dest position
   * @param destPinstanceIdosition - instance id
   ******************************************************************/
  array_move(arr, originalPosition, destPosition, instanceId: string, parentStoreKey?): Array<any> {

    let moved_node = arr[originalPosition]

    arr.splice(originalPosition, 1);

    arr.splice(destPosition, 0, moved_node);

    arr.forEach((item, index) => {
      if (item.position !== index + 1) {
        item.position = index + 1;
        item.updatePoistionFunction(this, item, instanceId, parentStoreKey);
      }
    });

    return arr;
  };

  drag(store, serviceModelId: string, fromObj): boolean {
    if(!this.checkFeatureFlag(Features.FLAG_1911_INSTANTIATION_ORDER_IN_ASYNC_ALACARTE)) {
      return;
    } else{
      if (this.checkFeatureFlag(Features.FLAG_2008_DISABLE_DRAG_FOR_BASE_MODULE)) {
        if(this.isBaseModule(serviceModelId,fromObj)) {
          return;
        }
      }
    }

  }
}
