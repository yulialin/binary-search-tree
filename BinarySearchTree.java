public class BinarySearchTree<TYPE extends Comparable> {
	protected BTNode<TYPE> root; // root of the tree

	public BinarySearchTree() { super(); root = null; }

	/**
	 Adds an element to the tree.

	 @param o an element that can be compared to others in the tree.
	 */
	public void addElement(TYPE o) {
		if (root == null) root = new BTNode<TYPE>(o,null,null);
		else addElement(root,o);
	}

	/*
       Helper method for adding element
       @param t root node of tree to which o is added.  Must NOT be null.
       @param o object to be inserted
     */
	private void addElement(BTNode<TYPE> t,TYPE o) {
		// TODO

		int comp = o.compareTo(t.element);
		if (comp < 0) { //if o is smaller than t, adds to the left of the node
			if (t.left == null) t.left = new BTNode<TYPE>(o,null, null);
			else addElement(t.left, o);    }
		else { //if o is bigger or equal to t, adds to the right of the node
			if (t.right == null) t.right = new BTNode<TYPE>(o, null, null);
			else addElement(t.right, o);  }
	}


	// o less than t.element
	// add to left subtree
	// if o is greater than or equal to t, add to right subtree

	/**
	 Return element that is equivalent to o.
	 Return null if no match is found.
	 @param o element to find.
	 */

	public TYPE getElement(TYPE o) {
		if (o == null) return null;
		else return getElement(root,o);
	}

	private TYPE getElement(BTNode<TYPE> t, TYPE o) {
		// TODO: There are three more cases!
		int comp = o.compareTo(t.element); //compare the value
		if (comp < 0) return getElement(t.left, o); //if o smaller than t, get to the left of the node
		else if (comp > 0) return getElement(t.right, o); //if o is equal or bigger than t, to the right
		else return t.element;
	}


	/**
	 Remove element with matching key and return it.
	 @returns First object in tree with matching key.  Returns null
	 if no match found.
	 */
	public TYPE  removeElement(TYPE o) {
		if (root == null || o == null) return null;
		else return removeElement(root,o);
	}


	/**

	 1. Locate the node you wish to delete.

	 2. If the node is a leaf, then disconnect it from its parent and set the
	 parent's pointer that pointed to it to null.

	 3. Otherwise if T has a right child, but no left child, then
	 remove T from the tree by making T's parent point to T's right child.

	 4. Otherwise if T has a left child but no right child, remove T from
	 the tree by making T's parent point to T's left child.

	 5. Finally, if T has both children, then find its logical
	 follower in the right subtree.  Copy the value of the follower
	 into T, then delete the follower.  Use recursion to delete the
	 follower.

	 */
	private TYPE removeElement(BTNode<TYPE> t, TYPE o) {
		// nothing to remove from empty tree
		System.out.println(t.element);
		System.out.print(t.toString());
		if (t == null) return null;

		BTNode<TYPE> parentofp = null;
		BTNode<TYPE> p = t;

		// TODO
		while (true) { //looping until hit the break
			if ((o.compareTo(p.element)) < 0) {
				if (p.left == null) return null;
				parentofp = p;
				p = p.left;
			}
			else if ((o.compareTo(p.element)) > 0) {
				if (p.right == null ) return null;
				parentofp = p;
				p = p.right;
			}
			if (((o.compareTo(p.element)) == 0) || (p == null)) {
				break;}
			} //breaks when match or null

			if (p.left == null && p.right == null) { //no child, leaf
				removeLeaf(p, parentofp);
			} else if (p.left == null || p.right == null) { //one child
				removeOneSubtree(p, parentofp);
			}else { //two children
				removeTwoSubtrees(p, parentofp);
			}
			return p.element;
		}
		// Find the node that is to be removed in a while loop.
		// Loop terminates when there is a match or null is encountered.
		// As you loop, you need to keep the parent of the current node, too.

		// Post-Loop INVARIANT: p is the node to delete.  parentofp is its parent

	/*
	  3 cases, EACH IS A SEPARATE METHOD
	  1. p is a leaf: removeLeaf(p,parentofp)
	  2. p has one child: removeOneSubtree(p,parentofp)
	  3. p has two children: removeTwoSubtrees(p,parentofp)

	  I recommend you write them in the order indicated above, since they
	  become successively more difficult and depend on one another.
	  Test as you go!!!
	*/



	/**
      Remove a leaf.  The node p must be a leaf
      and pp must be its parent node.
      If p is root, then root is set to null.
    */
	private void removeLeaf(BTNode p, BTNode pp) {

		if (p == root) {
			root = null;
		} //TODO
		else {
			if (pp.right == p) pp.right = null;
			else pp.left = null; //to see which side is the leaf on and set it to null
		}
	}

	/**
	 Remove p when it has only one subtree.
	 */
	private void removeOneSubtree(BTNode p, BTNode pp) {
		// TODO

		if (p == root) {
			if (p.left == null) {
				root = root.right;
			} else {
				root = root.left;
			} //checking which side is the subtree on

		} else if (p == pp.right) {
			if (p.left == null) { pp.right = p.right; p.right = null; }
			else { pp.right = p.left; p.left = null; }
		}
		else if (p == pp.left) {
			if (p.right == null) {pp.left = p.left; p.left = null; }
			else { pp.left = p.right; p.right = null; }
		}
	}

	/**
	 Remove p when it has two subtrees.  In this
	 case we replace material in p with greatest element in
	 p's left subtree (maxnode).
	 */
	private void removeTwoSubtrees(BTNode p, BTNode pp) {
		// TODO
		/*if(p == root){
			BTNode<TYPE> temp = p.left;
			root = p.right;
			addElement(temp.element);
		}
		//BTNode<TYPE> n = p.left;
		else if(pp.right == p){
			BTNode<TYPE> temp = p.left;
			pp.right = p.right;
			//pp.left = temp;
			addElement(temp.element);
		}

		else{
			BTNode<TYPE> temp = p.left;
			pp.left = p.left;
			addElement(temp.element);
		}
		*/
		BTNode n = p.left;
		BTNode pn = p; //pn is the parent node of p
		while (n.right != null) {
			pn = n;
			n = n.right;
		}
		p.element = n.element;

		if (p.right != null || p.left != null) {
			removeOneSubtree(n, pn);
		}
		else removeLeaf(n, pn);

	}





	/**
       Returns height of tree.  Returns -1 if tree has no node.
       sets heights in all encountered nodes.
    */
	private int height(BTNode t) {
		if (t == null) return -1;
		//int lefth = height()
		// TODO
		return 1 + Math.max(height(t.left), height(t.right));
	}


	/**
	 Find heights of all nodes in tree.
	 */
	public void computeNodeHeights() {
		height(root);
	}



	/******************************************************************/
	/* Graphics and display methods. */
	/******************************************************************/


	/**
	 Return map containing keys.  Map is used
	 to generate graphical representation of tree.
	 */
	public TYPE[][] getMap() {
		int maxsize = 50;
		int maxrow = height(root)+1; // maximum height + 1
		int maxcol = (int) Math.pow(2.0,maxrow); //max cols in binary tree


		TYPE[][] map = (TYPE[][]) (new Object[20][20]);
		// fill array with keys from tree nodes.
		maxcol = drawTree(root,map,0,0,maxrow);

		TYPE[][] newmap = (TYPE[][]) (new Object[maxrow][maxcol]);
		for (int i = 0; i < newmap.length; i++) {
			for (int j = 0; j < newmap[i].length; j++)
				newmap[i][j] = map[i][j];
		}
		return newmap;
	}


	/**
	 Draw a binary search tree to stdout. (text)
	 */
	public void showTree() {
		// map holds pointers to all objects in the tree
		// cannot handle more than 400 nodes on a page.
		//TYPE[][] map = (TYPE[][]) (new Object[20][20]);
		Object[][] map = new Object[20][20];
		int maxcol = 0;
		int maxrow = height(root)+1;
		// fixed field width for each node and for each blank
		final String padding = "   ";

		System.out.println("Tree height is " + (maxrow-1));

		// fill array with keys from tree nodes.
		maxcol = drawTree(root,map,0,maxcol,maxrow);

		// use keys in map to place
		for (int row = 0; row < maxrow; row++) {
			for (int col = 0; col < maxcol; col++) {
				// if no node, print padding
				if (map[row][col] == null) System.out.print(padding);
					// if there is a node print it in a field of
					//length padding.length
				else padPrint(map[row][col],padding.length());
			}
			System.out.println();
		}
	}

	/* print padding around strings */
	private void padPrint(Object o, int padlen) {
		String s = o.toString();
		System.out.print(s);
		int len = s.length();
		while (len++ < padlen) {
			System.out.print(' ');
		}

	}


	/*
      Position tree nodes in a 2D array by first position left subtree,
      then node, then right subtree.
      @returns col, the maximum column used.
     */
	private int drawTree(BTNode root, Object[][] map,
						 int row, int col, int maxrow) {
		// stop if leaf node or if map dimensions exceeded
		if (root == null) return col;
		if ((maxrow+1) >= map.length ||
				(col+1) >= map[0].length) {
			System.out.println("WARNING. Tree could not be completely printed.");
			return col;
		}
		// maximum row (level) containing a node
		if (row > maxrow) maxrow = row;

		col = drawTree(root.left,map,row+1,col,maxrow);
		map[row][col] = ((TYPE) root.element);
		col++;
		col = drawTree(root.right,map,row+1,col,maxrow);
		return col;
	}

	/**
	 Update drawing positions in tree prior to draw.
	 */
	public void updatePositions() {
		positionNodes(root,0,0);
	}

	/**
	 Update x,y in each node.
	 y is the row = node depth.
	 x is the col = #preceding nodes in left subtree + 1
	 */
	private int positionNodes(BTNode r, int row, int col) {
		// stop if leaf node
		if (r == null) return col;

		// set position left subtree nodes
		col = positionNodes(r.left,row+1,col);

		// set position of this node
		r.y = row;  //
		r.x = col;
		col++;
		// set pos of right subtree nodes
		col = positionNodes(r.right,row+1,col);
		return col;
	}

	/**
	 Inorder traversal.
	 */
	public void inOrder() {
		inOrderRec(root);
	}

	// recursive inorder traversal
	private void inOrderRec(BTNode<TYPE> t) {
		if (t == null) return;
		inOrderRec(t.left);
		System.out.print(t.element + " ");
		inOrderRec(t.right);
	}
}
