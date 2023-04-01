#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int top = -1;
int stack_arr[MAX];
void push(int item);
void pop();
void peek();
void display();
int isFull();
int isEmpty();
int isFull(){
	if(top == MAX - 1){
		return 1;
	}
	else{
		return 0;
	}
}
int isEmpty(){
	if(top == -1){
		return 1;
	}
	else{
		return 0;
	}
}
void display(){
	if(isEmpty()){
		printf("Stack Underflow\n");
	}
	else{
		int i;
		printf("Stack is:\n");
		for(int i = 0;i<=top;i++){
			printf("%d ",stack_arr[i]);
		}
		printf("\n");
	}
}
void push(int item){
	if(isFull()){
		printf("Stack Overflow\n");
	}
	else{
		top++;
		stack_arr[top]=item;
		printf("%d inserted",item);
	}
}
void pop(){
	if(isEmpty()){
		printf("Stack Underflow\n");
	}
	else{
		printf("%d popped",stack_arr[top]);
		top--;
		
	}
}
void peek(){
	if(isEmpty()){
		printf("Stack Underflow\n");
	}
	else{
		printf("Item at top: %d",stack_arr[top]);
	}
}
void printmenu(){
	printf("Press...\n1. Push\n2. Pop\n3. Peek\n4. Display\n5. Print Menu\n6. Quit\n");
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