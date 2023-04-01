/*
	Name: Double Linked List
	Author: Niladri Sekhar Mondal
	Date: 12-02-23
*/
#include<stdio.h>
#include<stdlib.h>
struct node{
	struct node *prev;
	int info;
	struct node *next;
};
void display(struct node *start)
{
	struct node *p;
	if(start==NULL)
	{
		printf("List is empty\n\n");
		return;
	}
	printf("Elements are: ");
	p = start;
	while(p!=NULL)
	{
		printf("%d ",p->info);
		p = p->next;
	}
	printf("\n\n");
}
void count(struct node *start)
{
	int cnt = 0;
	struct node *p = start;
	while(p!=NULL)
	{
		cnt++;
		p = p->next;
	}
	printf("No of elements: %d\n\n",cnt);
}
void search(struct node *start,int item)
{
	if(start == NULL)
	{
		printf("List is empty\n\n");
		return;
	}
	int pos = 1;
	struct node *p = start;
	while(p!=NULL)
	{
		if(p->info == item)
		{
			printf("%d was found on position %d\n\n",item,pos);
			return;
		}
		p = p->next;
		pos++;
	}
	printf("%d was not found",item);
}
struct node *addtoempty(struct node *start,int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->prev = NULL;
	tmp->next = NULL;
	start = tmp;
	return start;
}
struct node *addatbeg(struct node *start,int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->prev = NULL;
	tmp->next = start;
	start->prev = tmp;
	start = tmp;
	return start;
}
struct node *addatend(struct node *start,int data)
{
	struct node *tmp,*p = start;
	while(p->next!=NULL)
	{
		p = p->next;
	}
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->next = NULL;
	tmp->prev = p;
	p->next = tmp;
	return start;
}
struct node *addafter(struct node *start,int data,int item)
{
	struct node *tmp,*p = start;
	while(p!=NULL)
	{
		if(p->info==item)
		{
			tmp = (struct node*)malloc(sizeof(struct node));
			tmp->info = data;
			tmp->next = p->next;
			tmp->prev = p;
			if(p->next!=NULL)
			{
				p->next->prev = tmp;
			}
			p->next = tmp;
			return start;
		}
		p = p->next;
	}
	printf("%d was not found in the list",item);
	return start;
}
struct node *addbefore(struct node *start,int data,int item)
{
	struct node *tmp,*p;
	if(start == NULL)
	{
		printf("List is empty\n\n");
		return start;
	}
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	if(start->info == item)
	{
		tmp->next = start;
		tmp->prev = NULL;
		start->prev = tmp;
		start = tmp;
		return start;
	}
	p = start;
	while(p!=NULL)
	{
		if(p->info == item)
		{
			tmp->next = p;
			tmp->prev = p->prev;
			p->prev->next = tmp;
			p->prev = tmp;
			return start;
		}
		p = p->next;
	}
	printf("%d was not found in the list",item);
	return start;
}
struct node *del(struct node *start,int item)
{
	struct node *tmp;
	if(start == NULL)
	{
		printf("List is Empty\n\n");
		return start;
	}
	if(start->next == NULL)
	{
		if(start->info == item)
		{
			tmp = start;
			start = NULL;
			free(tmp);
			return start;
		}
	}
	if(start->info == item)
	{
		tmp = start;
		start = start->next;
		start->prev = NULL;
		free(tmp);
		return start;
	}
	tmp = start->next;
	while(tmp->next!=NULL)
	{
		if(tmp->info == item)
		{
			tmp->next->prev = tmp->prev;
			tmp->prev->next = tmp->next;
			free(tmp);
			return start;
		}
		tmp = tmp->next;
	}
	if(tmp->info == item)
	{
		tmp->prev->next = NULL;
		free(tmp);
		return start;
	}
	printf("%d was not found in the list\n\n",item);
	return start;
}
struct node *create_list(struct node *start)
{
	int i,n,data;
	printf("Enter the number of nodes you want to create: ");
	scanf("%d",&n);
	start = NULL;
	if(n==0)
	{
		return start;
	}
	printf("Enter value for the element: ");
	scanf("%d",&data);
	start = addtoempty(start,data);
	for(i = 2;i<=n;i++)
	{
		printf("Enter value for the element: ");
		scanf("%d",&data);
		start = addatend(start,data);
	}
	return start;
}
void options()
{
	printf("1.Create List\n");
	printf("2.Display\n");
	printf("3.Count number of elements\n");
	printf("4.Search\n");
	printf("5.Add at an empty list\n");
	printf("6.Add at beginning of the list\n");
	printf("7.Add at the end of the list\n");
	printf("8.Add after a perticular node\n");
	printf("9.Add before a particular node\n");
	printf("10.Delete a node\n");
	printf("11.Show options\n");
	printf("12.Quit\n");
}
int main()
{
	struct node *start = NULL;
	int choice,data,item;
	options();
	while(1)
	{
		printf("Enter your choice: ");
		scanf("%d",&choice);
		switch(choice)
		{
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
				printf("Enter the value of item to be searched: ");
				scanf("%d",&item);
				search(start,item);
				break;
			case 5:
				printf("Enter the value of the item to be added: ");
				scanf("%d",&data);
				start = addtoempty(start,data);
				break;
			case 6:
				printf("Enter the value of the item to be added: ");
				scanf("%d",&data);
				start = addatbeg(start,data);
				break;	
			case 7:
				printf("Enter the value of the item to be added: ");
				scanf("%d",&data);
				start = addatend(start,data);
				break;
			case 8:
				printf("Enter the value of the item to be added: ");
				scanf("%d",&data);
				printf("Enter the value of the item whose after the above value will be added: ");
				scanf("%d",&item);
				start = addafter(start,data,item);
				break;
			case 9:
				printf("Enter the value of the item to be added: ");
				scanf("%d",&data);
				printf("Enter the value of the item whose before the above value will be added: ");
				scanf("%d",&item);
				start = addbefore(start,data,item);
				break;
			case 10:
				printf("Enter the value of the item to be deleted: ");
				scanf("%d",&item);
				start = del(start,item);
				break;
			case 11:
				options();
				break;	
			case 12:
				exit(1);
			default:
				printf("Invalid Input\n");	
		}
	}
	return 0;
}