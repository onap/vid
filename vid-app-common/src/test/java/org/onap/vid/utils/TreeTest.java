package org.onap.vid.utils;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.*;

public class TreeTest {

    @NotNull
    protected Tree<String> buildTreeForTest() {
        Tree<String> tree = new Tree<>("a");
        tree.addPath("b","c","d");
        tree.addPath("b","cc","dd");
        tree.addPath("1","2","dd");
        return tree;
    }

    @DataProvider
    public static Object[][] pathsToFind() {
        return new Object[][]{
                {ImmutableList.of("b","c","d"), true},
                {ImmutableList.of("b","c"), true},
                {ImmutableList.of("b","cc","dd"), true},
                {ImmutableList.of("b","cc","d"), false},
                {ImmutableList.of("1","2","dd"), true},
                {ImmutableList.of("b"), true},
                {ImmutableList.of("c"), false},
                {ImmutableList.of("z", "z", "z", "z", "z"), false},
        };
    }

    @Test(dataProvider="pathsToFind")
    public void whenBuildTree_nodesFoundsInRoute(List<String> path, boolean isFound) {
        Tree<String> tree = buildTreeForTest();
        assertEquals(isFound, tree.isPathExist(path));
    }

    @Test(dataProvider="pathsToFind")
    public void whenBuildTree_subTreeGetRight(List<String> path, boolean isFound) {
        Tree<String> tree = buildTreeForTest();
        if (isFound) {
            assertNotNull(tree.getSubTree(path));
        }
        else {
            assertNull(tree.getSubTree(path));
        }
    }

    @Test
    public void whenBuildTree_getSubTreeAsExpected() {
        Tree<String> tree = buildTreeForTest();
        Tree<String> subTree = tree.getSubTree("b","c");
        assertEquals(subTree.getRootValue(), "c");
        assertTrue(subTree.isPathExist("d"));
        assertFalse(subTree.isPathExist("b","c","d"));
    }
}