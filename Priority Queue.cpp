#include<stdio.h>
#include<stdlib.h>
struct node{
	int priority;
	int info;
	struct node *link;
}*front = NULL;
void insert(int item_priority,int item);
void del();
void display();
int isEmpty();
int isEmpty(){
	if(front==NULL){
		return 1;
	}
	else{
		return 0;
	}
}
int main(){
	int item,item_priority,choice;
	while(1){
		printf("1. Insert\n2. Delete\n3. Display\n4. Quit\n");
		printf("Enter your choice: ");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				printf("Enter the priority: ");
				scanf("%d",&item_priority);
				printf("\nEnter data to insert: ");
				scanf("%d",&item);
				insert(item_priority,item);
				break;
			case 2:
				del();
				break;
			case 3:
				display();
				break;
			case 4:
				exit(1);
			default:
				printf("Invalid Input\n");
		}
	}
	return 0;
}
void insert(int item_priority,int item){
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = item;
	if(tmp==NULL){
		printf("Memory is Full\n");
		return;
	}
	if(front==NULL || item_priority<front->priority){
		tmp->priority=item_priority;
		tmp->link=front;
		front = tmp;
	}
	else{
		struct node *p = front;
		while(p->link!=NULL && p->link->priority<=item_priority){
			p = p->link;
		}
		tmp->priority=item_priority;
		tmp->link = p->link;
		p->link = tmp;
	}
	printf("%d is inserted with priority %d\n",item,item_priority);
}
void del(){
	if(isEmpty()){
		printf("Queue Underflow\n");
		return;
	}
	struct node *tmp = front;
	front = front->link;
	printf("%d is deleted\n",tmp->info);
	free(tmp);
}
void display(){
	if(isEmpty()){
		printf("Queue is Empty\n");
	}
	else{
		printf("Priority		Data\n");
		struct node *tmp = front;
		while(tmp!=NULL){
			printf("%d		%d\n",tmp->priority,tmp->info);
			tmp = tmp->link;
		}
	}
}