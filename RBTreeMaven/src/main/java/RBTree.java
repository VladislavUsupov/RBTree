public class RBTree
{
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;

    static
    {
        nullNode = new RedBlackNode(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    private static final int BLACK = 1;
    private static final int RED   = 0;

    public RBTree(int negInf)
    {
        header = new RedBlackNode(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }



    public void insert(int item )
    {
        current = parent = grand = header;
        nullNode.element = item;

        while (current.element != item)
        {
            great = grand;
            grand = parent;
            parent = current;
            current = item < current.element ?
                    current.left : current.right;

            if (current.left.color == RED && current.right.color == RED)
                handleReorient( item );
        }

        if (current != nullNode)
            return;
        current = new RedBlackNode(item, nullNode, nullNode);

        if (item < parent.element)
            parent.left = current;
        else
            parent.right = current;
        handleReorient( item );
    }



    public boolean search(int val)
    {
        return search(header.right, val);
    }



    public int countNodes()
    {
        return countNodes(header.right);
    }



    public boolean isEmpty()
    {
        return header.right == nullNode;
    }



    public void clear()
    {
        header.right = nullNode;
    }



    public void printTree()
    {
        printTree(header.right);
    }



    private void handleReorient(int item)
    {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            grand.color = RED;
            if (item < grand.element != item < parent.element)
                parent = rotate( item, grand );
            current = rotate(item, great );
            current.color = BLACK;
        }
        header.right.color = BLACK;
    }



    private RedBlackNode rotate(int item, RedBlackNode parent)
    {
        if(item < parent.element)
            return parent.left = item < parent.left.element ?
                    rotateWithLeftChild(parent.left) :
                    rotateWithRightChild(parent.left) ;
        else
            return parent.right = item < parent.right.element ?
                    rotateWithLeftChild(parent.right) :
                    rotateWithRightChild(parent.right);
    }



    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
    {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }



    private RedBlackNode rotateWithRightChild(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }



    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }



    private boolean search(RedBlackNode r, int val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            if (val < r.element)
                r = r.left;
            else if (val > r.element)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }



    private void printTree(RedBlackNode r)
    {
        if (r != nullNode)
        {
            printTree(r.left);
            printTree(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
        }
    }
}