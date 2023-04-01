#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int queue_arr[MAX];
int front = -1,rear = -1;
void display();
int isEmpty();
int isFull();
void enqueue(int item);
void dequeue();
void peek();
void printmenu();
void printmenu(){
	printf("Press...\n1. Display\n2. Insert\n3. Delete\n4. Peek\n5. Print Menu\n6. Quit\n");
}
int isEmpty(){
	if(front == -1 || front == rear +1){
		return 1;
	}
	else{
		return 0;
	}
}
int isFull(){
	if(rear == MAX-1){
		return 1;
	}
	else{
		return 0;
	}
}
void display(){
	if(isEmpty()){
		printf("Queue Underflow\n");
	}
	else{
		printf("List is...\n");
		int i;
		for(i = front;i<=rear;i++){
			printf("%d	",queue_arr[i]);
		}
		printf("\n");
	}
}
void enqueue(int item){
	if(isFull()){
		printf("Queue Overflow\n");
	}
	else{
		if(front == -1){
			front = 0;
		}
		rear++;
		queue_arr[rear]=item;
		printf("%d inserted\n",item);
	}
}
void dequeue(){
	if(isEmpty()){
		printf("Queue Underflow\n");
	}
	else{
		printf("%d deleted\n",queue_arr[front]);
		front++;
	}
}
void peek(){
	if(isEmpty()){
		printf("Queue Underflow\n");
	}
	else{
		printf("Peek element: %d",queue_arr[front]);
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