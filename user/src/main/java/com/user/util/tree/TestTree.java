package com.user.util.tree;

import java.util.Objects;

public class TestTree {

    public static void main(String[] args) {
        BaseTree baseTree  = new BaseTree();
        for (int i = 0; i < 5; i++) {
            BaseTree leftTree  = new BaseTree();
            BaseTree rightTree  = new BaseTree();
            Integer valueInt = baseTree.getValueInt();
            if(Objects.isNull(valueInt)){
                valueInt = 0;
                baseTree.setValueInt(valueInt);
                continue;
            }
            BaseTree node = baseTree.getNode();
            if(Objects.isNull(node)){
                leftTree.setValueInt(i);
                baseTree.setNode(leftTree);
                continue;
            }

            if(valueInt < i){
                rightTree.setValueInt(valueInt);
                baseTree.setValueInt(i);
                baseTree.setNode(rightTree);
            }
        }
        System.out.println(baseTree);
    }
}
