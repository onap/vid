export default
  {
    "modelInfo": {
      "modelType": "service",
      "modelInvariantId": "5d48acb5-097d-4982-aeb2-f4a3bd87d31b",
      "modelVersionId": "3c40d244-808e-42ca-b09a-256d83d19d0a",
      "modelName": "MOW AVPN vMX BV vPE 1 Service",
      "modelVersion": "10.0"
    },
    "owningEntityId": "038d99af-0427-42c2-9d15-971b99b9b489",
    "owningEntityName": "JULIO ERICKSON",
    "projectName": "{some project name}",
    "globalSubscriberId": "{some subscriber id}",
    "productFamilyId": "a9a77d5a-123e-4ca2-9eb9-0b015d2ee0fb",
    "instanceName": "vPE_Service",
    "subscriptionServiceType": "VMX",
    "lcpCloudRegionId": "mdt1",
    "tenantId": "88a6ca3ee0394ade9403f075db23167e",
    "vnfs": [
      {
        "modelInfo": {
          "modelName": "2016-73_MOW-AVPN-vPE-BV-L",
          "modelVersionId": "7f40c192-f63c-463e-ba94-286933b895f8",
          "modelCustomizationName": "2016-73_MOW-AVPN-vPE-BV-L 0",
          "modelCustomizationId": "ab153b6e-c364-44c0-bef6-1f2982117f04"
        },
        "lcpCloudRegionId": "mdt1",
        "tenantId": "88a6ca3ee0394ade9403f075db23167e",
        "platformName": "test",
        "productFamilyId": "a9a77d5a-123e-4ca2-9eb9-0b015d2ee0fb",
        "instanceName": "vmxnjr001",
        "instanceParams": [],
        "vfModules": [
          {
            "modelInfo": {
              "modelType": "vfModule",
              "modelName": "201673MowAvpnVpeBvL..AVPN_base_vPE_BV..module-0",
              "modelVersionId": "4c75f813-fa91-45a4-89d0-790ff5f1ae79",
              "modelCustomizationId": "a25e8e8c-58b8-4eec-810c-97dcc1f5cb7f"
            },
            "instanceName": "vmxnjr001_AVPN_base_vPE_BV_base_001",
            "instanceParams": [
              {
                "vmx_int_net_len": "24"
              }
            ]
          }
        ]
      }
    ]
  }

