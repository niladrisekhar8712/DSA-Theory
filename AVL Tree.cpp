#include<stdio.h>
#include<stdlib.h>
struct node {
	int data;
	int balance;
	struct node *left;
	struct node *right;
};
struct node *search(struct node *root, int key) {
    if (root == NULL || root->data == key) {
        return root;
    }

    if (key < root->data) {
        return search(root->left, key);
    } else {
        return search(root->right, key);
    }
}
void display(struct node *root) {
    if (root != NULL) {
        display(root->left);
        printf("%d ", root->data);
        display(root->right);
    }
}
void clear(struct node *root) {
    if (root != NULL) {
        clear(root->left);
        clear(root->right);
        free(root);
    }
}
struct node *RotateRight(struct node *pptr){
	struct node *aptr;
	aptr = pptr->left;
	pptr->left = aptr->right;
	aptr->right = pptr;
	return aptr;
}
struct node *RotateLeft(struct node *pptr){
	struct node *aptr;
	aptr = pptr->right;
	pptr->right = aptr->left;
	aptr->left = pptr;
	return aptr;
}
struct node *del_LeftBalance(struct node *pptr, bool *shorter){
	struct node *aptr = pptr->left;
	if(aptr->balance == 1){
		pptr->balance = 0;
		aptr->balance = 0;
		pptr = RotateRight(pptr);
	}
	else if(aptr->balance == 1){
		pptr->balance = 0;
		aptr->balance = 0;
		*shorter = false;
		pptr = RotateRight(pptr);
	}
	else{
		struct node *bptr = aptr->right;
		switch(bptr->balance){
			case -1:
				pptr->balance = 0;
				aptr->balance = 1;
				break;
			case 1:
				pptr->balance = -1;
				aptr->balance = 0;
				break;
			case 0:
				pptr->balance = 0;
				aptr->balance = 0;
		}
		bptr->balance = 0;
		pptr->left = RotateLeft(aptr);
		pptr = RotateRight(pptr);
	}
	return pptr;
}
struct node *del_right_check(struct node *pptr, bool *shorter){
	switch(pptr->balance){
		case 0:
			pptr->balance = 1;
			*shorter = false;
			break;
		case -1:
			pptr->balance = 0;
			break;
		case 1:
			pptr = del_LeftBalance(pptr,shorter);
	}
	return pptr;
}
struct node *del_RightBalance(struct node *pptr,bool *shorter){
	struct node *aptr = pptr->right;
	if(aptr->balance == -1){
		pptr->balance = 0;
		aptr->balance = 0;
		pptr = RotateLeft(pptr);		
	}
	else if(aptr->balance == 0){
		pptr->balance = -1;
		aptr->balance = 1;
		*shorter = false;
		pptr = RotateLeft(pptr);
	}
	else{
		struct node *bptr = aptr->left;
		switch(bptr->balance){
			case -1:
				pptr->balance = 1;
				aptr->balance = 0;
				break;
			case 1:
				pptr->balance = 0;
				aptr->balance = -1;
				break;
			case 0:
				pptr->balance = 0;
				aptr->balance = 0;
		}
		bptr->balance = 0;
		pptr->right = RotateRight(aptr);
		pptr = RotateLeft(pptr);
	}
	return pptr;
}
struct node *del_left_check(struct node *pptr, bool *shorter){
	switch(pptr->balance){
		case 0:
			pptr->balance = -1;
			*shorter = false;
			break;
		case 1:
			pptr->balance = 0;
			break;
		case -1:
			pptr = del_RightBalance(pptr,shorter);
			*shorter = false;
	}
	return pptr;
}
struct node *del(struct node *pptr, int dkey){
	struct node *tmp,*succ;
	static bool shorter;
	if(pptr == NULL){
		printf("Key not found\n");
		shorter = false;
		return pptr;
	}
	if(dkey < pptr->data){
		pptr->left = del(pptr->left,dkey);
		if(shorter){
			pptr = del_left_check(pptr,&shorter);
		}
	}
	else if(dkey > pptr->data){
		pptr->right = del(pptr->right,dkey);
		if(shorter){
			pptr = del_right_check(pptr,&shorter);
		}
	}
	else{
		if(pptr->left !=NULL && pptr->right != NULL){
			succ = pptr->right;
			while(succ->right){
				succ = succ->left;
			}
			pptr->data = succ->data;
			pptr->right = del(pptr->right, succ->data);
			if(shorter){
				pptr = del_right_check(pptr,&shorter);
			}
		}
		else{
			tmp = pptr;
			if(pptr->left != NULL){
				pptr  = pptr->left;
			}
			else if(pptr->right != NULL){
				pptr  = pptr->right;
			}
			else pptr = NULL;
			free(tmp);
			shorter = true;
		}
	}
	return pptr;
}

struct node *insert_RightBalance(struct node *pptr){
	struct node *aptr = pptr->right;
	if(aptr->balance == -1){
		pptr->balance = 0;
		aptr->balance = 0;
		pptr = RotateLeft(pptr);		
	}
	else{
		struct node *bptr = aptr->left;
		switch(bptr->balance){
			case -1:
				pptr->balance = 1;
				aptr->balance = 0;
				break;
			case 1:
				pptr->balance = 0;
				aptr->balance = -1;
				break;
			case 0:
				pptr->balance = 0;
				aptr->balance = 0;
		}
		bptr->balance = 0;
		pptr->right = RotateRight(aptr);
		pptr = RotateLeft(pptr);
	}
	return pptr;
}
struct node *insert_right_check(struct node *pptr, bool *taller){
	switch(pptr->balance){
		case 0:
			pptr->balance = -1;
			break;
		case 1:
			pptr->balance = 0;
			*taller = false;
			break;
		case -1:
			pptr = insert_RightBalance(pptr);
			*taller = false;
	}
	return pptr;
}
struct node *insert_LeftBalance(struct node *pptr){
	struct node *aptr = pptr->left;
	if(aptr->balance == 1){
		pptr->balance = 0;
		aptr->balance = 0;
		pptr = RotateRight(pptr);		
	}
	else{
		struct node *bptr = aptr->right;
		switch(bptr->balance){
			case -1:
				pptr->balance = 0;
				aptr->balance = 1;
				break;
			case 1:
				pptr->balance = -1;
				aptr->balance = 0;
				break;
			case 0:
				pptr->balance = 0;
				aptr->balance = 0;
		}
		bptr->balance = 0;
		pptr->left = RotateLeft(aptr);
		pptr = RotateRight(pptr);
	}
	return pptr;
}
struct node *insert_left_check(struct node *pptr, bool *taller){
	switch(pptr->balance){
		case 0:
			pptr->balance = 1;
			break;
		case -1:
			pptr->balance = 0;
			*taller = false;
			break;
		case 1:
			pptr = insert_LeftBalance(pptr);
			*taller = false;
	}
	return pptr;
}
struct node *insert(struct node *root, int ikey){
	static bool taller;
	if(root == NULL){
		root = (struct node *)malloc(sizeof(struct node));
		root->data = ikey;
		root->balance = 0;
		root->left = NULL;
		root->right = NULL;
		taller = false;
	}
	else if(root->data > ikey){
		root->left = insert(root->left,ikey);
		if(taller){
			root = insert_left_check(root,&taller);
		}
	}
	else if(root->data < ikey){
		root->right = insert(root->right,ikey);
		if(taller){
			root = insert_right_check(root,&taller);
		}
	}
	else{
		printf("Duplicate Key\n");
		taller = false;
	}
	return root;
}
int main() {
    struct node *root = NULL;
    int choice, key;
    while (true) {
        printf("\nAVL Tree Operations:\n");
        printf("1. Insert a node\n");
        printf("2. Delete a node\n");
        printf("3. Search for a node\n");
        printf("4. Display the tree\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter the key to insert: ");
                scanf("%d", &key);
                root = insert(root, key);
                break;
            case 2:
                printf("Enter the key to delete: ");
                scanf("%d", &key);
                root = del(root, key);
                break;
            case 3:
                printf("Enter the key to search: ");
                scanf("%d", &key);
                if (search(root, key)) {
                    printf("Key %d found in the tree.\n", key);
                } else {
                    printf("Key %d not found in the tree.\n", key);
                }
                break;
            case 4:
                display(root);
                break;
            case 5:
                clear(root);
                exit(0);
            default:
                printf("Invalid choice! Please enter a number between 1 and 5.\n");
        }
    }
    return 0;
}