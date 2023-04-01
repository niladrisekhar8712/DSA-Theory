#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int front = -1;
int rear = -1;
int cqueue_arr[MAX];
void display();
void enqueue(int item);
void dequeue();
void peek();
int isFull();
int isEmpty();
void printmenu();
void printmenu(){
	printf("1. Insert\n2. Delete\n3. Item at front\n4. Print Menu\n5. Quit\n");
}
void enqueue(int item){
	if(isFull()){
		printf("Queue Overflow\n");
		return;
	}
	if(front == -1){
		front = 0;
	}
	else if(rear = MAX-1){
		rear=0;
	}
	else{
		rear++;
	}
	cqueue_arr[rear]=item;
}
void dequeue(){
	if(isEmpty()){
		printf("Queue Underflow\n");
		return;
	}
	if(front==rear){
		front = -1;
		rear = -1;
	}
	else if(fornt = MAX-1){
		front = 0;
	}
	else{
		rear++;
	}
	printf("Item deleted\n");
}
void peek(){
	if(isEmpty()){
		printf("Queue is Empty\n");
		return;
	}
	printf("Item at front: %d\n",cqueue_arr[front]);
}
int isFull(){
	if((front == 0 && rear = MAX-1)|| front = )
}