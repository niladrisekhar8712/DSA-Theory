#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define MAX 20
char stack[MAX];
int top = -1;
void push(char);
char pop();
int main(){
	int i;
	char str[MAX];
	printf("Enter a string: ");
	gets(str);
	for(i=0;i<strlen(str);i++){
		push(str[i]);
	}
	for(i=0;i<strlen(str);i++){
		str[i]=pop();
	}
	printf("Reversed String: ");
	puts(str);
	return 0;
}
void push(char item){
	if(top == MAX-1){
		printf("Stack is Full\n");
	}
	else{
		stack[++top]=item;
	}
}
char pop(){
	if(top == -1){
		printf("Stack is Empty\n");
		exit(1);
	}
	return stack[top--];
}