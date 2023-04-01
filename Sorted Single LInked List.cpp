#include<stdio.h>
#include<stdlib.h>
struct node{
	int info;
	struct node *link;
};
struct node *insert(struct node *start,int data);
void search(struct node *start,int data);
void display(struct node *start);
struct node *del(struct node *start,int data);
void printmenu();
void printmenu(){
	printf("1. Insert\n2. Search\n3. Delete\n4. Display\n5. Quit\n");
}
struct node *insert(struct node *start,int data){
	struct node *tmp,*p;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info=data;
	if(start==NULL||data<start->info){		/*insert at begin*/
		tmp->link = start;
		start = tmp;
		return start;
	}
	p=start;
	while(p->link!=NULL && data<p->info){
		p=p->link;
	}
	tmp->link = p->link;
	p->link = tmp;
	return start;
}
void search(struct node *start,int data){
	if(start == NULL || data<start->info){
		printf("%d not found in the list\n",data);
		return;
	}
	else{
		int pos = 1;
		struct node *p = start;
		while(p!=NULL && data<p->info){
			if(p->info==data){
				printf("%d found at %d position in the list\n",data,pos);
				return;
			}
			p=p->link;
			pos++;
		}
	}
	printf("%d not found in the list\n",data);
}
struct node *del(struct node *start,int data){
	struct node *tmp;
	if(start->info == data){	/*Deletion of first node*/
		tmp = start;
		start = start->link;
		free(tmp);
		return start;
	}
	struct node *p = start;
	while(p->link!=NULL){		/*Deletion in between*/
		if(p->link->info==data){
			tmp = p->link;
			p->link = tmp->link;
			free(tmp);
			return start;	
		}
		p = p->link;
	}
	printf("%d not found in the list",data);
	return start;
}
void display(struct node *start){
	if(start == NULL){
		printf("List is Empty\n");
	}
	else{
		printf("List is:\n");
		struct node *tmp = start;
		while(tmp!=NULL){
			printf("%d	",tmp->info);
			tmp= tmp->link;
		}
		printf("\n");
	}
}
int main(){
	struct node *start = NULL;
	int choice,data;
	printmenu();
	while(1){
		printf("Press: ");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				printf("Enter element: ");
				scanf("%d",&data);
				start = insert(start,data);
				printf("%d inserted\n",data);
				break;
			case 2:
				printf("Enter element: ");
				scanf("%d",&data);
				search(start,data);
				break;
			case 3:
				printf("Enter element: ");
				scanf("%d",&data);
				start = del(start,data);
				break;
			case 4:
				display(start);
				break;
			case 5:
				exit(1);
			default:
				printf("Invalid Input\n");
		}
	}
}