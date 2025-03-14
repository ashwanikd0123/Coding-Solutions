public class RecoverBST {

    TreeNode a;
    TreeNode b;
    TreeNode c;

    TreeNode inorderPred;

    void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (inorderPred != null && root.val < inorderPred.val) {
            if (a == null) {
                a = inorderPred;
                b = root;
            } else {
                c = root;
            }
        }
        inorderPred = root;
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        a = b = inorderPred = null;
        inorder(root);
        if (c == null) {
            swap(a, b);
        } else {
            swap(a, c);
        }
    }
}
