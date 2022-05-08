package com.icewalnut.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LC106 从中序和后序遍历构造二叉树
 */
public class BuildTreeFromInorderAndPostorder {
    // 用来存放 中序遍历 ，求 root 节点位置
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] post;			// 用一个成员变量来代替传参， 存放 原来的 postorder，因为要不停地从 post 中拿值

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            // 将中序遍历的节点值和索引记录在哈希表中，索引为 value
            map.put(inorder[i], i);
        }
        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    public TreeNode buildTree(int l1, int r1, int l2, int r2) {
        // 递归终止条件
        // 注意是  后序遍历 按从后往前遍历的
        if (l1 > r1 || l2 > r2) {
            return null;
        }

        // 根据后序遍历结果，取得根节点的值，注意这里的 root 不是 TreeNode ，是值
        int root = post[r2];
        // 根据 哈希表 获取索引
        int mid = map.get(root);

        TreeNode node = new TreeNode(root);		// 创建该节点
        // 左子树，递归
        node.left = buildTree(l1, mid - 1, l2, l2 + mid - l1 - 1);
        // 右子树，递归
        node.right = buildTree(mid + 1, r2, l2 + mid - l1, r2 - 1);
        return node;
    }
}
