#include<stdio.h>
#include<stdlib.h>
void display();
void push(int item);
void pop();
void peek();
struct node{
	int info;
	struct node *link;
}*top=NULL;
void display(){
	if(top==NULL){
		printf("Stack Underflow\n");
	}
	else{
		struct node *tmp = top;
		while(tmp != NULL){
			printf("%d ",tmp->info);
			tmp=tmp->link;
		}
		printf("\n");
	}
}
void push(int item){
	struct node *tmp = (struct node*)malloc(sizeof(struct node));
	if(tmp==NULL){
		printf("Stack Overflow\n");
	}
	else{
		tmp->info = item;
		tmp->link = top;
		top=tmp;
	}
}
void pop(){
	if(top==NULL){
		printf("Stack Underflow\n");
	}
	else{
		struct node *tmp;
		tmp = top;
		top = top->link;
		free(tmp);
	}
}
void peek(){
	if(top==NULL){
		printf("Stack Underflow\n");
	}
	else{
		printf("Item at top: %d\n",top->info);
	}
}
void printmenu(){
	printf("Press...\n1. Push\n2. Pop\n3.Peek\n4. Display\n5. Printmenu\6. Quit\n");
}
int main(){
	int choice;
	printmenu();
	while(1){
		printf("Enter your choice: ");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				int item;
				printf("Enter item: ");
				scanf("%d",&item);
				printf("\n");
				push(item);
				break;
			case 2:
				pop();
				break;
			case 3:
				peek();
				break;
			case 4:
				display();
				break;
			case 5:
				printmenu();
				break;	
			case 6:
				exit(1);
			default:
				printf("Invalid Input\n");					
		}
	}
}