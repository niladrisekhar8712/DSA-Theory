#include<stdio.h>
#include<stdlib.h>
struct node{
	int info;
	struct node *link;
}*front = NULL,*rear = NULL;
void display();
void enqueue(int item);
void dequeue();
void peek();
int isEmpty();
void printmenu();
void printmenu(){
	printf("Press...\n1. Display\n2. Insert\n3. Delete\n4. Peek\n5. Print Menu\n6. Quit\n");
}
void enqueue(int item){
	struct node *tmp = (struct node*)malloc(sizeof(struct node));
	if(tmp==NULL){
		printf("Memory not available\n");
	}
	else{
		tmp->info = item;
		tmp->link = NULL;
		if(front==NULL){
			front = tmp;
		}
		else{
			rear->link = tmp;
		}
		rear = tmp;
	}
}
void dequeue(){
	if(isEmpty()){
		printf("Queue Underflow\n");
	}
	else{
		struct node *tmp;
		tmp = front;
		front = front->link;
		printf("%d deleted\n",tmp->info);
		free(tmp);
	}
}
void peek(){
	if(isEmpty()){
		printf("Queue is Empty\n");
	}
	else{
		printf("Item at front: %d\n",front->info);
	}
}
void display(){
	if(isEmpty()){
		printf("Queue is Empty\n");
	}
	else{
		struct node *tmp = front;
		while(tmp != NULL){
			printf("%d	",tmp->info);
			tmp = tmp->link;
		}
		printf("\n");
	}
}
int isEmpty(){
	if(front==NULL){
		return 1;
	}
	else{
		return 0;
	}
}
int main(){
	int choice,item;
	printmenu();
	while(1){
		printf("\nEnter your choice: ");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				display();
				break;
			case 2:
				printf("Enter item: ");
				scanf("%d",&item);
				enqueue(item);
				break;
			case 3:
				dequeue();
				break;
			case 4:
				peek();
				break;
			case 5:
				printmenu();
				break;
			case 6:
				exit(1);
			default:
				printf("Invalid Input\n\n");			
		}
	}
}