#include<stdio.h>
#include<stdlib.h>
struct node{
	int info;
	struct node *link;
};
void display(struct node *start);
struct node *create_list(struct node *start);
void count(struct node *start);
void search(struct node *start,int item);
struct node *addatbegin(struct node *start, int data);
struct node *addatend(struct node *start, int data);
struct node *addafter(struct node *start, int data, int item);
struct node *addbefore(struct node *start, int data, int item);
struct node *addatpos(struct node *start, int data, int pos);
struct node *del(struct node *start, int item);
void print_menu();
int main()
{
	struct node *start = NULL;
	int item,pos,data,choice;
	print_menu();
	while(1)
	{
		printf("Enter choice: \n");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				start = create_list(start);
				break;
			case 2:
				display(start);
				break;
			case 3:
				count(start);
				break;
			case 4:
				printf("Enter the element to search: \n");
				scanf("%d",&item);
				search(start,item);
				break;
			case 5:
				printf("Enter element to add: \n");
				scanf("%d",&data);
				start = addatbegin(start,data);
				break;
			case 6:
				printf("Enter element to add: \n");
				scanf("%d",&data);
				start = addatend(start,data);
				break;
			case 7:
				printf("Enter element the add: \n");
				scanf("%d",&data);
				printf("Enter the node: \n");
				scanf("%d",&item);
				start = addafter(start,data,item);
				break;
			case 8:
				printf("Enter element the add: \n");
				scanf("%d",&data);
				printf("Enter the node: \n");
				scanf("%d",&item);
				start = addbefore(start,data,item);
				break;
			case 9:
				printf("Enter element the add: \n");
				scanf("%d",&data);
				printf("Enter the position: \n");
				scanf("%d",&pos);
				start = addbefore(start,data,pos);
				break;
			case 10:
				printf("Enter element to delete: \n");
				scanf("%d",&item);
				start = del(start,item);
				break;
			case 11:
				print_menu();
				break;
			case 12:
				exit(1);
			default:
				printf("Invalid Input\n");
		}
	}
	return 0;
}
void print_menu()
{
	printf("Available Options: \n");
	printf("Press: \n");
	printf("1. Create List \n");
	printf("2. Display \n");
	printf("3. Count no of elements \n");
	printf("4. Search for an item \n");
	printf("5. Add item at begin \n");
	printf("6. Add item at end \n");
	printf("7. Add item after a node \n");
	printf("8. Add item before a node \n");
	printf("9. Add item at a specific position \n");
	printf("10. Delete an item \n");
	printf("11. Print Menu");
	printf("12. Quit \n");
}
void display(struct node *start)
{
	if(start == NULL){
		printf("List is empty\n");
		return;
	}
	struct node *p = start;
	printf("Elements are: \n");
	while(p != NULL){
		printf("%d ",p->info);
		p = p->link;
	}
	printf("\n");
}
void count(struct node *start)
{
	struct node *p = start;
	int count = 0;
	while(p != NULL){
		p = p->link;
		count++;
	}
	printf("Number of elements in the list: %d\n",count);
}
void search(struct node *start, int item)
{
	struct node *p = start;
	int pos=1;
	while(p != NULL){
		if(p->info == item){
			printf("Element %d found at position %d\n",item,pos);
			return;
		}
		p = p->link;
		pos++;
	}
	printf("Element %d was not found\n",item);
}
struct node *addatbegin(struct node *start,int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->link = start;
	start = tmp;
	return start;
}
struct node *addatend(struct node *start, int data)
{
	struct node *p,*tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	p=start;
	while(p->link != NULL){
		p = p->link;
	}
	p->link = tmp;
	tmp->link=NULL;
	return start;
}
struct node *addafter(struct node *start, int data, int item)
{
	struct node *p,*tmp;
	p=start;
	while(p != NULL){
		if(p->info == item){
			tmp->link = p->link;
			tmp = (struct node*)malloc(sizeof(struct node));
			tmp->info = data;
			tmp->link=p->link;
			p->link = tmp;
			return start;
		}
		p=p->link;
	}
	printf("%d is not in the list\n",item);
	return start;
}
struct node *addbefore(struct node *start, int data, int item)
{
	if(start == NULL){
		printf("List is empty\n");
		return start;
	}
	struct node *p,*tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info == data;
	if(start->info == item){
		tmp->link = start;
		start = tmp;
		return start;
	}
	p = start;
	while(p->link != NULL){
		p = p->link;
		if(p->link->info == item){
			tmp->link = p->link;
			p->link= tmp;
			return start;
		}
		p = p->link;
	}
	printf("Item %d is not present in the list\n",item);
	return start;
}
struct node *addatpos(struct node *start, int data, int pos)
{
	struct node *p,*tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	if(pos==1){
		tmp->link = start->link;
		start = tmp;
		return start;
	}
	p = start;
	for(int i = 1;i<pos-1 && p != NULL;i++){
		p = p->link;
	}
	if(p == NULL){
		printf("There are less than %d elements in the list",pos);
		return start;
	}
	else{
		tmp->link = p->link;
		p->link = tmp;
	}
	return start;
}
struct node *del(struct node *start, int item){
	struct node *p,*tmp;
	if(start == NULL){
		printf("List is empty\n");
	}
	if(start->info == item){
		tmp = start;
		start= start->link;
		free(tmp);
		return start;
	}
	while(p != NULL){
		if(p->link->info = item){
			tmp = p->link;
			p->link = tmp->link;
			free(tmp);
			return start;
		}
	}
	printf("%d is not in the list",item);
	return start;
}
struct node *create_list(struct node *start){
	int n,i,data;
	printf("\nEnter the  number of nodes: ");
	scanf("%d",&n);
	start = NULL;
	if(n==0){
		return start;
	}
	printf("\nEnter element");
	scanf("%d",&data);
	start = addatbegin(start,data);
	for(i=2;i<=n;i++){
		printf("\nEnter element: ");
		scanf("%d",&data);
		start = addatend(start,data);
	}
	return start;
}