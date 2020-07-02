package assignment3.part1;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private boolean color;
		
		@SuppressWarnings("unused")
		public Node(Key key, Value value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}
	
	private Node root;
	
	private static boolean RED = true;
	private static boolean BLACK = false;
	
	public Value get(Key key) {
		
		if (key == null) 
			throw new IllegalArgumentException();
			
		
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if (node == null)
			return null;
		
		int cmp = key.compareTo(node.key);
		
		if (cmp < 0)
			return get(node.left, key);
		else if (cmp > 0)
			return get(node.right, key);
		else
			return node.value;
	}
	
	public void put(Key key, Value value) { 
		if (key == null)
			throw new IllegalArgumentException();
		
		if (value == null) {
			delete(key);
			return;
		}
		
		root = put(root, key, value);
		
		root.color = BLACK;
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null)
			return new Node(key, value, RED);
		
		int cmp = key.compareTo(node.key);
		
		if (cmp < 0)
			node.left = put(node.left, key, value);
		else if (cmp > 0) 
			node.right = put(node.right, key, value);
		else 
			node.value = value;
		
		// Need to check if it satisfies the rules of RBT's
		// 1. No right-leaning red link. If there are, rotate left.
		if (!isRed(node.left) && isRed(node.right)) {
			node = rotateLeft(node);
		}
		
		// 2. No two (valid) red links (ie: two left leaning red links 
		// in a row). If there are, rotate right.
		if (isRed(node.left) && isRed(node.left.left))
			node = rotateRight(node);
		
		// 3. No two red links to both children. 
		// If there are, flip.
		if (isRed(node.left) && isRed(node.right))
			flipColors(node);
		
		
		return node;
	}
	
	private Node rotateLeft(Node node) {
		// You have a right-leaning red link.
		
		Node temp = node.right;			// Temp -> right child
		node.right = temp.left;			// Replace right child, with its own left child
		temp.left = node;				// Raise temp (Node is now its child)
		temp.color = node.color;
		node.color = RED;
		return temp;
	}
	
	private Node rotateRight(Node node) {
		Node temp = node.left;
		node.left = temp.right;
		temp.right = node;
		temp.color = root.color;
		root.color = RED;
		return temp;
	}
	
	private void flipColors(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
		
	}
	
	private boolean isRed(Node node) {
		if (node == null)
			return BLACK;
		else
			return node.color;
	}
	
	public boolean isBalanced() {
		int blackHeight = 0;
		
		Node temp = root;
		
		while (temp != null) {
			if (!isRed(temp))
				blackHeight++;
			temp = temp.left;
		}
		
		return isBalanced(root, blackHeight);
	}
	
	private boolean isBalanced(Node node, int blackHeight) {
		
		if (root == null)
			return blackHeight==0;
		
		if (!isRed(node))
			blackHeight--;
		
		return isBalanced(root.left, blackHeight) && isBalanced(root.right, blackHeight);
	}
	
	public void delete(Key key) {}
		/*
		if (key == null)
			throw new IllegalArgumentException();
		
		if (get(key) == null)
			return;
		
		// if both children of node are black, set node to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = delete(root, key);
		if (root != null)
			root.color = BLACK;
	}
	
	private Node delete(Node node, Key key) {
	
		int cmp = key.compareTo(node.key);
		
		if (cmp < 0) {
			
			if (!isRed(node.left) && !isRed(node.left.left))
				node = moveRedLeft(node);
			
			node.left = delete(node.left, key);
			
		} else {
			if (isRed(node.left))
				node = rotateRight(node);
			
			if ((cmp == 0) && (node.right == null))
				return null;
			
			if (!isRed(node.right) && !isRed(node.right.left))
				node = moveRedRight(node);
			
			if (cmp == 0) {
				Node x = min(node.right);
				node.key = x.key;
				node.value = x.value;
				
				node.right = deleteMin(node.right);
			}
			else
				node.right = delete(node.right, key);
		}
		
		
	}
	*/
	
	
	
	
	
	
	
	
	


}
