public class BSTSerializeDeserialize {
    public class Codec {

        static final String DEL = " ";

        StringBuilder preorder;

        int idx;

        void buildPreorder(TreeNode root) {
            if (root == null) {
                return;
            }
            preorder.append(root.val).append(DEL);
            buildPreorder(root.left);
            buildPreorder(root.right);
        }

        int[] convert(String dataStr) {
            String[] vals = dataStr.split(DEL);
            int[] res = new int[vals.length];
            for (int i = 0; i < vals.length; i++) {
                res[i] = Integer.parseInt(vals[i]);
            }
            return res;
        }

        TreeNode buildTree(int[] preorder, int lower, int upper) {
            if (idx == preorder.length || preorder[idx] < lower || preorder[idx] > upper) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[idx]);
            idx++;
            root.left = buildTree(preorder, lower, root.val);
            root.right = buildTree(preorder, root.val, upper);
            return root;
        }

        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            preorder = new StringBuilder();
            buildPreorder(root);
            if (preorder.length() > 0) {
                preorder.deleteCharAt(preorder.length() - 1);
            }
            return preorder.toString();
        }

        public TreeNode deserialize(String dataStr) {
            if (dataStr.isEmpty()) {
                return null;
            }
            int[] preorder = convert(dataStr);
            idx = 0;
            return buildTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}
