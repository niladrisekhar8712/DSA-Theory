/*
	Name: BINARY SEARCH TREE OPERATIONS
	Author: NILADRI SEKHAR MONDAL
	Date: 01-04-23
*/
#include<stdio.h>
#include<stdlib.h>
struct node{
	int data;
	struct node *lchild;
	struct node *rchild;
};
int Height(struct node *root)
{
	int h_left,h_right;
	struct node *tmp = root;
	if(tmp == NULL)
	{
		return 0;
	}
	h_left = Height(tmp->lchild);
	h_right = Height(tmp->rchild);
	if(h_left>h_right)
	{
		return 1+ h_left;
	}
	else
	{
		return 1+ h_right;
	}
}
struct node *min(struct node *root)
{
	struct node *ptr = root;
	if(ptr!=NULL)
	{
		while(ptr->lchild!=NULL)
		{	
			ptr = ptr->lchild;
		}	
	}
	return ptr;
}
struct node *max(struct node *root)
{
	struct node *ptr = root;
	if(ptr!=NULL)
	{
		while(ptr->rchild!=NULL)
		{	
			ptr = ptr->rchild;
		}	
	}
	return ptr;
}
struct node *insert(struct node *root,int ikey)
{
	struct node *ptr,*par,*tmp;
	ptr = root;
	par = NULL;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->data = ikey;
	tmp->lchild = NULL;
	tmp->rchild = NULL;
	while(ptr!=NULL)
	{
		par = ptr;
		if(ikey>ptr->data)
		{
			ptr = ptr->rchild;
		}
		else if(ikey<ptr->data)
		{
			ptr = ptr->lchild;
		}
		else
		{
			printf("Duplicate Key\n");
			return root;
		}
	}
	if(par == NULL)
	{
		root = tmp;
	}
	else if(ikey<par->data)
	{
		par->lchild = tmp;
	}
	else if(ikey>par->data)
	{
		par->rchild = tmp;
	}
	return root;
}
struct node *search(struct node *root,int skey)
{
	struct node *ptr = root;
	while(ptr!=NULL)
	{
		if(skey>ptr->data)
		{
			ptr = ptr->rchild;
		}
		else if(skey<ptr->data)
		{
			ptr = ptr->lchild;
		}
		else
		{
			return ptr;
		}
	}
	return NULL;
}
void inorder(struct node *ptr)
{
	if(ptr==NULL)
	{
		return;
	}
	inorder(ptr->lchild);
	printf("%d	",ptr->data);
	inorder(ptr->rchild);
}
void preorder(struct node *ptr)
{
	if(ptr==NULL)
	{
		return;
	}
	printf("%d	",ptr->data);
	preorder(ptr->lchild);
	preorder(ptr->rchild);
}
void postorder(struct node *ptr)
{
	if(ptr==NULL)
	{
		return;
	}
	postorder(ptr->lchild);
	postorder(ptr->rchild);
	printf("%d	",ptr->data);
}
struct node *case_a(struct node *root, struct node *par, struct node *ptr)
{
	if(par == NULL)
	{
		root = NULL;
	}
	else if(par->lchild == ptr)
	{
		par->lchild == NULL;
	}
	else
	{
		par->rchild == NULL;
	}
	free(ptr);
	return root;
}
struct node *case_b(struct node *root, struct node *par, struct node *ptr)
{
	struct node *child;
	if(par->lchild!=NULL)
	{
		child = ptr->lchild;
	}
	else
	{
		child = ptr->rchild;
	}
	if(par == NULL)
	{
		root = child;
	}
	else if(ptr == par->lchild)
	{
		par->lchild = child;
	}
	else
	{
		par->rchild = child;
	}
	free(ptr);
	return root;
}
struct node *case_c(struct node *root, struct node *par, struct node *ptr)
{
	struct node *succ,*parsucc;
	parsucc = ptr;
	succ = ptr->rchild;
	while(succ->lchild!=NULL)
	{
		parsucc = succ;
		succ = succ->lchild;
	}
	ptr->data = succ->data;
	if(succ->lchild==NULL && succ->rchild==NULL)
	{
		root = case_a(root,parsucc,succ);
	}
	else
	{
		root = case_b(root,parsucc,succ);
	}
	return root;
}
struct node *del(struct node *root, int dkey)
{
	struct node *ptr,*par;
	ptr = root;
	par = NULL;
	while(ptr!=NULL)
	{
		if(ptr->data == dkey)
		{
			break;
		}
		par = ptr;
		if(dkey<ptr->data)
		{
			ptr = ptr->lchild;
		}
		else
		{
			ptr = ptr->rchild;
		}
	}
	if(ptr == NULL)
	{
		printf("%d not present\n",dkey);
	}
	else if(par->lchild!=NULL && par->rchild!=NULL)
	{
		root = case_c(root,par,ptr);
	}
	else if(par->lchild!=NULL)
	{
		root = case_b(root,par,ptr);
	}
	else if(par->rchild!=NULL)
	{
		root = case_b(root,par,ptr);
	}
	else
	{
		root = case_a(root,par,ptr);
	}
	return root;
}
void options()
{
	printf("1. Search\n");
	printf("2. Insert\n");
	printf("3. Preorder Traversal\n");
	printf("4. Inorder Traversal\n");
	printf("5. Postorder Traversal\n");
	printf("6. Height of The Tree\n");
	printf("7. Minimum and Maximum Node\n");
	printf("8. Delete\n");
	printf("9. Print Options\n");
	printf("10. Quit\n");
	printf("\n");
}
int main()
{
	int ch,k;
	struct node *root = NULL,*ptr;
	options();
	while(1)
	{
		printf("Enter your choice: ");
		scanf("%d",&ch);
		switch(ch)
		{
			case 1:
				printf("Enter the key to be searched: ");
				scanf("%d",&k);
				ptr = search(root,k);
				if(ptr == NULL)
				{
					printf("%d not found\n",k);
				}
				else
				{
					printf("%d is found\n",k);
				}
				break;
			case 2:
				printf("Enter the key to be inserted: ");
				scanf("%d",&k);
				root = insert(root,k);
				break;
			case 3:
				preorder(root);
				printf("\n");
				break;
			case 4:
				inorder(root);
				printf("\n");
				break;
			case 5:
				postorder(root);
				printf("\n");
				break;
			case 6:
				printf("The height of the tree: %d\n",Height(root));
				break;
			case 7:
				ptr = min(root);
				if(ptr!=NULL)
				{
				printf("Minimum element is %d\n",ptr->data);
				}
				ptr = max(root);
				if(ptr!=NULL)
				{
					printf("Maximum element is %d\n",ptr->data);
				}
				break;
			case 8:
				printf("Enter the node to be deleted: ");
				scanf("%d",&k);
				root = del(root,k);
				break;
			case 9:
				options();
				break;
			case 10:
				exit(1);
			default:
				printf("Invalid Input\n");
		}
	}
}