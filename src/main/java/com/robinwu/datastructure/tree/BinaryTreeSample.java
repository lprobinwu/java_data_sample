package com.robinwu.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 11/21/15.
 * http://blog.csdn.net/fightforyourdream/article/details/16843303
 *
 * 1. 求二叉树中的节点个数: getNodeNumRec（递归），getNodeNum（迭代）
 * 2. 求二叉树的深度: getDepthRec（递归），getDepth
 * 3. 前序遍历，中序遍历，后序遍历: preorderTraversalRec, preorderTraversal, inorderTraversalRec, postorderTraversalRec
 * (https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_2)
 * 4.分层遍历二叉树（按层次从上往下，从左往右）: levelTraversal, levelTraversalRec（递归解法！）
 * 5. 将二叉查找树变为有序的双向链表: convertBST2DLLRec, convertBST2DLL
 * 6. 求二叉树第K层的节点个数：getNodeNumKthLevelRec, getNodeNumKthLevel
 * 7. 求二叉树中叶子节点的个数：getNodeNumLeafRec, getNodeNumLeaf
 * 8. 判断两棵二叉树是否相同的树：isSameRec, isSame
 * 9. 判断二叉树是不是平衡二叉树：isAVLRec
 * 10. 求二叉树的镜像（破坏和不破坏原来的树两种情况）：mirrorRec, mirrorCopyRec
 * 10.1 判断两个树是否互相镜像：isMirrorRec
 * 11. 求二叉树中两个节点的最低公共祖先节点：getLastCommonParent, getLastCommonParentRec, getLastCommonParentRec2
 * 12. 求二叉树中节点的最大距离：getMaxDistanceRec
 * 13. 由前序遍历序列和中序遍历序列重建二叉树：rebuildBinaryTreeRec
 * 14.判断二叉树是不是完全二叉树：isCompleteBinaryTree, isCompleteBinaryTreeRec  *
 */
public class BinaryTreeSample {

    /*
                 1
                / \
               2   3
              / \   \
             4  5   6
     */
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.right = r6;

//      System.out.println(getNodeNumRec(r1));
//      System.out.println(getNodeNum(r1));
//      System.out.println(getDepthRec(r1));
//      System.out.println(getDepth(r1));

//      preorderTraversalRec(r1);
//      System.out.println();
//      preorderTraversal(r1);
//      System.out.println();
//      inorderTraversalRec(r1);
//      System.out.println();
//      inorderTraversal(r1);
//      System.out.println();
//      postorderTraversalRec(r1);
//      System.out.println();
//      postorderTraversal(r1);
//      System.out.println();
//      levelTraversal(r1);
//      System.out.println();
//      levelTraversalRec(r1);
//      System.out.println();

      TreeNode tmp = convertBST2DLLRec(r1);
      while(true){
          if(tmp == null){
              break;
          }
          System.out.print(tmp.val + " ");
          if(tmp.right == null){
              break;
          }
          tmp = tmp.right;
      }
//      System.out.println();
//      while(true){
//          if(tmp == null){
//              break;
//          }
//          System.out.print(tmp.val + " ");
//          if(tmp.left == null){
//              break;
//          }
//          tmp = tmp.left;
//      }


//      TreeNode tmp = convertBST2DLL(r1);
//      while(true){
//          if(tmp == null){
//              break;
//          }
//          System.out.print(tmp.val + " ");
//          if(tmp.right == null){
//              break;
//          }
//          tmp = tmp.right;
//      }

//      System.out.println(getNodeNumKthLevelRec(r1, 2));
//      System.out.println(getNodeNumKthLevel(r1, 2));

//      System.out.println(getNodeNumLeafRec(r1));
//      System.out.println(getNodeNumLeaf(r1));

//      System.out.println(isSame(r1, r1));
//      inorderTraversal(r1);
//      System.out.println();
//      mirror(r1);
//      TreeNode mirrorRoot = mirrorCopy(r1);
//      inorderTraversal(mirrorRoot);

//        System.out.println(isCompleteBinaryTree(r1));
//        System.out.println(isCompleteBinaryTreeRec(r1));

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 求二叉树中的节点个数递归解法： O(n)
     * （1）如果二叉树为空，节点个数为0
     * （2）如果二叉树不为空，二叉树节点个数 = 左子树节点个数 +
     *            右子树节点个数 + 1
     */
    public static int getNodeNumRec(TreeNode root) {
        if (root == null) return 0;
        return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
    }

    /**
     *  求二叉树中的节点个数迭代解法O(n)：基本思想同LevelOrderTraversal，
     *  即用一个Queue，在Java里面可以用LinkedList来模拟
     */
    public static int getNodeNum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            count++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

    /**
     * 求二叉树的深度（高度） 递归解法： O(n)
     * （1）如果二叉树为空，二叉树的深度为0
     * （2）如果二叉树不为空，二叉树的深度 = max(左子树深度， 右子树深度) + 1
     */
    public static int getDepthRec(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepthRec(root.left), getDepthRec(root.right)) + 1;
    }

    /**
     * 求二叉树的深度（高度） 迭代解法： O(n)
     * 基本思想同LevelOrderTraversal，还是用一个Queue
     */
    public static int getDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        int depth = 0;               // the depth
        int countOfCurrentLevel = 1; // the node count of the current level
        int countOfNextLevel = 0;    // the node count of the next level

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            countOfCurrentLevel--;

            if (node.left != null) {
                queue.add(node.left);
                countOfNextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                countOfNextLevel++;
            }
            if(countOfCurrentLevel == 0) {
                depth++;
                countOfCurrentLevel = countOfNextLevel;
                countOfNextLevel = 0;
            }
        }

        return depth;
    }

    /**
     * 前序遍历，中序遍历，后序遍历 前序遍历递归解法：
     * （1）如果二叉树为空，空操作
     * （2）如果二叉树不为空，访问根节点，前序遍历左子树，前序遍历右子树
     */
    public static void preorderTraversalRec(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorderTraversalRec(root.left);
        preorderTraversalRec(root.right);
    }

    /**
     *  前序遍历迭代解法：用一个辅助stack，总是把右孩子放进栈
     *  push right child first and then the left child
     *  http://www.youtube.com/watch?v=uPTCbdHSFg4
     */
    public static void preorderTraversal(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            // 关键点：要先压入右孩子，再压入左孩子，这样在出栈时会先打印左孩子再打印右孩子
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历递归解法
     * （1）如果二叉树为空，空操作。
     * （2）如果二叉树不为空，中序遍历左子树，访问根节点，中序遍历右子树
     */
    public static void inorderTraversalRec(TreeNode root) {
        if (root == null) return;
        inorderTraversalRec(root.left);
        System.out.print(root.val + " ");
        inorderTraversalRec(root.right);
    }

    /**
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内，
     * 然后输出栈顶元素，再处理栈顶元素的右子树
     * http://www.youtube.com/watch?v=50v1sJkjxoc
     */
    public static void inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    /**
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内，
     * 然后输出栈顶元素，再处理栈顶元素的右子树
     * http://www.youtube.com/watch?v=50v1sJkjxoc
     */
    public static void inorderTraversal1(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (true) {
            while (node != null) {
                // 先添加一个非空节点所有的左孩子到栈
                stack.push(node);
                node = node.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            // 因为此时已经没有左孩子了，所以输出栈顶元素
            node = stack.pop();
            System.out.print(node.val + " ");
            // 准备处理右子树
            node = node.right;
        }
    }

    /**
     * 后序遍历递归解法
     * （1）如果二叉树为空，空操作
     * （2）如果二叉树不为空，后序遍历左子树，后序遍历右子树，访问根节点
     */
    public static void postorderTraversalRec(TreeNode root) {
        if (root == null) return;
        postorderTraversalRec(root.left);
        postorderTraversalRec(root.right);

        System.out.print(root.val + " ");
    }

    /**
     *  后序遍历迭代解法
     *  http://www.youtube.com/watch?v=hv-mJUs5mvU
     *  This is the opposite of preorder traversal
     *
     */
    public static void postorderTraversal(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<TreeNode>();  // 第一个stack用于添加node和它的左右孩子
        Stack<TreeNode> output = new Stack<TreeNode>(); // 第二个stack用于翻转第一个stack输出

        stack.push(root);

        while (!stack.isEmpty()) {              // 确保所有元素都被翻转转移到第二个stack
            TreeNode node = stack.pop();        // 把栈顶元素添加到第二个stack
            output.push(node);

            // 把栈顶元素的左孩子和右孩子分别添加入第一个stack
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        while (!output.isEmpty()) {          // 遍历输出第二个stack，即为后序遍历
            System.out.print(output.pop().val + " ");
        }
    }

    // TODO: another solution for postorderTraversal

    /**
     * 分层遍历二叉树（按层次从上往下，从左往右）迭代
     * 相当于广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点
     * ，访问，若左子节点或右子节点不为空，将其压入队列
     */
    public static void levelTraversal(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            System.out.print(node.val + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    /**
     *  分层遍历二叉树（递归）
     *  很少有人会用递归去做level traversal
     *  基本思想是用一个大的ArrayList，里面包含了每一层的ArrayList。
     *  大的ArrayList的size和level有关系
     *
     */
    public static void levelTraversalRec(TreeNode root) {
        if (root == null) return;

        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        dfs(root, 0, ret);
        System.out.println(ret);
    }

    private static void dfs(TreeNode root, int level, ArrayList<ArrayList<Integer>> ret) {
        if (root == null) return;
        if (level >= ret.size()) {        // 添加一个新的ArrayList表示新的一层
            ret.add(new ArrayList<Integer>());
        }
        ret.get(level).add(root.val);     // 把节点添加到表示那一层的ArrayList里
        dfs(root.left, level + 1, ret);   // 递归处理下一层的左子树和右子树
        dfs(root.right, level + 1, ret);
    }

    /**
     * 将二叉查找树变为有序的双向链表 要求不能创建新节点，只调整指针。
     * 递归解法：
     * 参考了http://stackoverflow.com/questions/11511898/converting-a-binary-search-tree-to-doubly-linked-list#answer-11530016
     * 感觉是最清晰的递归解法，但要注意递归完，root会在链表的中间位置，因此要手动
     * 把root移到链表头或链表尾
     */
    public static TreeNode convertBST2DLLRec(TreeNode root) {
        TreeNode node = convertBST2DLLSubRec(root);
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     *  递归转换BST为双向链表(DLL)
     */
    public static TreeNode convertBST2DLLSubRec(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        if (root.left != null) {      // 处理左子树
            TreeNode temp = convertBST2DLLSubRec(root.left);
            while (temp.right != null) {   // 寻找最右节点
                temp = temp.right;
            }
            root.left = temp;         // 把左子树处理后结果和root连接
            temp.right = root;
        }
        if (root.right != null) {     // 处理右子树
            TreeNode temp = convertBST2DLLSubRec(root.right);
            while (temp.left != null) {    // 寻找最左节点
                temp = temp.left;
            }
            root.right = temp;        // 把右子树处理后结果和root连接
            temp.left = root;
        }
        return root;
    }

    /**
     * TODO
     * @param root
     * @return
     */
    public static TreeNode convertBST2DLL(TreeNode root) {
        if (root == null) return null;

        TreeNode head = null;
        TreeNode pre = null;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (true) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (stack.isEmpty()) {
                break;
            }

            cur = stack.pop();
            if (head == null) {
                head = cur;
            } else {
                pre.right = cur;
                cur.left = pre;
            }
            pre = cur;

            cur = cur.right;
        }

        return head;
    }

    /**
     * 求二叉树第K层的节点个数   递归解法
     * Note. the first level is the root level.
     * @return the count of nodes
     */
    public static int getNodeNumKthLevelRec(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int leftCount = getNodeNumKthLevelRec(root.left, k - 1);
        int rightCount = getNodeNumKthLevelRec(root.right, k - 1);

        return leftCount + rightCount;
    }

    /**
     * 求二叉树第K层的节点个数
     * Non-Recursive solution
     * Note. the first level is the root level.
     * @return the count of nodes
     */
    public static int getNodeNumKthLevel(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty() && k > 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            k--;
        }

        return queue.size();
    }

    public static int getNodeNumKthLevelRec1(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }

        int[] res = new int[1];
        dfs1(root, 1, k, res);

        return res[0];
    }

    private static void dfs1(TreeNode node, int level, int k, int[] res) {
        if (level == k) {
            res[0]++;
            return;
        }

        if (node.left != null) {
            dfs1(node.left, level + 1, k, res);
        }
        if (node.right != null) {
            dfs1(node.right, level + 1, k, res);
        }
    }

    /**
     * 求二叉树中叶子节点的个数（递归）
     */
    public static int getNodeNumLeafRec(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return getNodeNumLeafRec(root.left) + getNodeNumLeafRec(root.right);
    }

    /**
     *  求二叉树中叶子节点的个数（迭代）
     *  还是基于Level order traversal
     */
    public static int getNodeNumLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left == null && node.right == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * 判断两棵二叉树是否相同的树。
     * 递归解法：
     * （1）如果两棵二叉树都为空，返回真
     * （2）如果两棵二叉树一棵为空，另一棵不为空，返回假
     * （3）如果两棵二叉树都不为空，如果对应的左子树和右子树都同构返回真，其他返回假
     */
    public static boolean isSameRec(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 == null || r2 == null || r1.val != r2.val) {
            return false;
        }

        return isSameRec(r1.left, r2.left) && isSameRec(r1.right, r2.right);
    }

    /**
     * 判断两棵二叉树是否相同的树（迭代）
     * 遍历一遍即可，这里用preorder
     */
    public static boolean isSame(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 == null || r2 == null) {
            return false;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        s1.push(r1);
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s2.push(r2);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode node1 = s1.pop();
            TreeNode node2 = s2.pop();

            if (node1 == null && node2 == null) {
                continue;
            } else if (node1 != null && node2 != null && node1.val == node2.val) {
                s1.push(node1.right);
                s1.push(node1.left);
                s2.push(node2.right);
                s2.push(node2.left);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断二叉树是不是平衡二叉树 递归解法：
     * （1）如果二叉树为空，返回真
     * （2）如果二叉树不为空，如果左子树和右子树都是AVL树并且左子树和右子树高度相差不大于1，返回真，其他返回假
     */
    public static boolean isAVLRec(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(getDepthRec(root.left) - getDepthRec(root.right)) > 1) {
            return false;
        }

        return isAVLRec(root.left) && isAVLRec(root.right);
    }

    /**
     * 求二叉树的镜像 递归解法：
     * （1）如果二叉树为空，返回空
     * （2）如果二叉树不为空，求左子树和右子树的镜像，然后交换左子树和右子树
     */
    // 1. 破坏原来的树，把原来的树改成其镜像
    public static TreeNode mirrorRec(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = mirrorRec(root.right);
        TreeNode right = mirrorRec(root.left);

        root.left = left;
        root.right = right;

        return root;
    }

    // 2. 不能破坏原来的树，返回一个新的镜像树
    public static TreeNode mirrorCopyRec(TreeNode root){
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = mirrorCopyRec(root.right);
        newRoot.right = mirrorCopyRec(root.left);

        return newRoot;
    }

    // 3. 判断两个树是否互相镜像
    public static boolean isMirrorRec(TreeNode r1, TreeNode r2){
        if (r1 == null || r2 == null) {
            return (r1 == r2);
        }
        if (r1.val != r2.val) {
            return false;
        }
        return isMirrorRec(r1.left, r2.right) && isMirrorRec(r1.right, r2.left);
    }

    // 1. 破坏原来的树，把原来的树改成其镜像
    // Change root in this case
    public static void mirror(TreeNode root) {
        if (root == null) {
            return;
        }

        // Note that change queue to stack
        // will work as well
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            // change left and right here.
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

    // 2. 不能破坏原来的树，返回一个新的镜像树
    public static TreeNode mirrorCopy(TreeNode root){
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        s1.push(root);
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s2.push(newRoot);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();

            if (n1.left != null) {
                s1.push(n1.left);
                n2.right = new TreeNode(n1.left.val);
                s2.push(n2.right);
            }
            if (n1.right != null) {
                s1.push(n1.right);
                n2.left = new TreeNode(n1.right.val);
                s2.push(n2.left);
            }
        }

        return newRoot;
    }

}

