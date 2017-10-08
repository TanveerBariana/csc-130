import java.io.*;
//BST code with reBalance methods implemented 
public class AVLTree_Two {
	protected BSTNode rootnode; // define root node
	class BSTNode {
	   public BSTNode left;
	   public BSTNode right;
	   public BSTNode root;
	   public int value;
	   public int balance;
	   public BSTNode(int k) {
			left = right = root = null; //initially set left, right and root to null
			value = k;
	   }
	   public String toString() {
			return "" + value;
	   }
	}
	// Insert mehod
	public void insert(int k) {
		 // create new node
		 BSTNode n = new BSTNode(k);
		 // recursively call insert mehod
		 insertBST(this.rootnode,n);
	}
	public void insertBST(BSTNode t, BSTNode x) {
		 if(t==null){
		   this.rootnode=x;
		 }else{
			   if(x.value<t.value){ //if entered value x < existing node value then go to left
					  if(t.left==null){
						  t.left = x;
						  x.root = t; 
						  reBalance(t);
					  }else{
						 insertBST(t.left,x);
					  }
			   }else if(x.value>t.value){ //if entered value x > existing node value then go to right
					  if(t.right==null) {
						   t.right = x;
						   x.root = t;
						   reBalance(t);
					  }else{
						 insertBST(t.right,x);
					  }
			   }else{
				  x.root = t;
			   }
		 }
	}
	
		// balance the violating subtree
	public void reBalance(BSTNode avl) {
		setBalance(avl);
		int balance = avl.balance;
		if (balance == -2) {
			if (height(avl.left.left) >= height(avl.left.right)) {
				avl = singleRotateWithRight(avl);
			} else {
				avl = doubleRotateWithRight(avl);
			}
		} else if (balance == 2) {
			if (height(avl.right.right) >= height(avl.right.left)) {
				avl = singleRotateWithLeft(avl);
			} else {
				avl = doubleRotateRightLeft(avl);
			}
		}
		if (avl.root != null) {
			reBalance(avl.root);
		} else {
			this.rootnode = avl;
			System.out.println("AVL Tree is balanced\n\n");
		}
	}
	
	public BSTNode singleRotateWithLeft(BSTNode t) {
		BSTNode t1 = t.right;
		t1.root = t.root;
		t.right = t1.left;
		if (t.right != null) {
			t.right.root = t;
		}
		t1.left = t;
		t.root = t1;
		if (t1.root != null) {
			if (t1.root.right == t) {
				t1.root.right = t1;
			} else if (t1.root.left == t) {
				t1.root.left = t1;
			}
		}
		setBalance(t);
		setBalance(t1);
		return t1;
	}
	
	public BSTNode singleRotateWithRight(BSTNode t) {
		BSTNode t1 = t.left;
		t1.root = t.root;
		t.left = t1.right;
		if (t.left != null) {
			t.left.root = t;
		}
		t1.right = t;
		t.root = t1;
		if (t1.root != null) {
			if (t1.root.right == t) {
				t1.root.right = t1;
			} else if (t1.root.left == t) {
				t1.root.left = t1;
			}
		}
		setBalance(t);
		setBalance(t1);
		return t1;
	}
	
	
	public BSTNode doubleRotateWithRight(BSTNode k3) {
		k3.left = singleRotateWithLeft(k3.left);
		return singleRotateWithRight(k3);
	}
	
	public BSTNode doubleRotateRightLeft(BSTNode k3) {
		k3.right = singleRotateWithRight(k3.right);
		return singleRotateWithLeft(k3);
	}
	//height() method, which is used in calculating the balance factor between two subtrees of a parent AVLNode
	private int height(BSTNode avl) {
		if (avl == null) {
			return -1;
		}
		if (avl.left == null && avl.right == null) {
			return 0;
		} else if (avl.left == null) {
			return 1 + height(avl.right);
		} else if (avl.right == null) {
			return 1 + height(avl.left);
		} else {
			return 1 + maximum(height(avl.left), height(avl.right));
		}
	}
	// method to remove node from BST
	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}
	
	// check the violation through difference of height of left and right
	private void setBalance(BSTNode avl) {
		avl.balance = height(avl.right) - height(avl.left);
	}
	public void remove(int k) {
		 // First we must find the node, after this we can delete it.
		 removeBST(this.rootnode,k);
		 
	}
	public void removeBST(BSTNode t,int x) {
		 if(t==null) {
			return ;
		 } else { 
			 if(t.value>x) {
				removeBST(t.left,x);
			 } else if(t.value<x) {
				removeBST(t.right,x);
			 } else if(t.value==x) {
				removeFoundNode(t);
			 }
		 }
	}
	public void removeFoundNode(BSTNode x) {
		 BSTNode r;
		 if(x.left==null || x.right==null) {
			 // the rootnode is deleted
			 if(x.root==null) {
				 this.rootnode=null;
				 x=null;
				 return;
			 }
			 r = x;
		 } else {
			 r = successor(x);
			 x.value = r.value;
		 }
		 BSTNode t;
		 if(r.left!=null) {
			t = r.left;
		 } else {
			t = r.right;
		 }
		 if(t!=null) {
			t.root = r.root;
		 }
		 if(r.root==null) {
			this.rootnode = t;
		 } else {
			 if(r==r.root.left) {
				r.root.left=t;
			 } else {
				r.root.right = t;
			 }
		 }
		 reBalance(r);
		 r = null;
		 
	}
	//find the node in a way to violating node
	public BSTNode successor(BSTNode x) {
		if(x.right!=null) {
			 BSTNode r = x.right;
			 while(r.left!=null) {
				r = r.left;
			 }
		return r;
		} else {
			 BSTNode t = x.root;
			 while(t!=null && x==t.right) {
				 x = t;
				 t = x.root;
			 }
			 return t;
		 }
	}
	//Search a node
	public void find(BSTNode n) {
		 int l = 0;
		 int r = 0;
		 int t = 0;
		 if(n.left!=null) {
			l = n.left.value;
		 }
		 if(n.right!=null) {
			r = n.right.value;
		 }
		 if(n.root!=null) {
			t = n.root.value;
		 }
		 if(n.left!=null) {
			find(n.left);
		 }
		 if(n.right!=null) {
			find(n.right);
		 }
	}
	//Returns tree in INORDER traversal
	private void inOrder(BSTNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.value);
			inOrder(node.right);
		}
	}
	void inOrder() {
		inOrder(rootnode);
	}
	//Check for Tree is Empty or not
	boolean isEmpty() {
		return rootnode == null;
	}
	
	public static void main(String[] args) {
		AVLTree_Two tree = new AVLTree_Two3();
		BufferedReader inputReader = new BufferedReader(new
		InputStreamReader(System.in));
		int choice = -1;
		int value;
		while (true) {
			 System.out.println("Select an option"); //Evertime user have to select one option
			System.out.println("1-Insert a node"); //At a time user can insert only one node
			System.out.println("2-Remove a node"); //At a time user can remove only one node
			System.out.println("3-Display tree in INORDER Traversal");
			System.out.println("0-EXIT");
			try {
				choice =Integer.valueOf(inputReader.readLine()).intValue();
				switch (choice) {
					case 0:
					System.out.println("Exit");
					return;
					case 1:
					System.out.println("Enter a value to insert");
					value = Integer.parseInt(inputReader.readLine());
					tree.insert(value);
					break;
					case 2:
					 if (tree.isEmpty()) {
						System.out.println("Sorry.....Can not delete anything tree is empty\n\n");
					} else {System.out.println("Enter a value to remove: ");
						value = Integer.parseInt(inputReader.readLine());
						tree.remove(value);
					}
					break;
					case 3:
					if (tree.isEmpty()) {
						System.out.println("Sorry....in order traversal not possible..AVL Tree is empty!\n\n");
					} else {
						System.out.println("The inorder Traversal of AVL Tree is:");
						tree.inOrder();
						System.out.println();
					}
					break;
					default:
					System.out.println("Invalid option. \n Try Again!!!");
				}
			} catch (IOException e) {
				e.printStackTrace();} catch (NumberFormatException e) {
				System.out.println("Incorrect choice");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}