/*
	Name: Circular Linked List
	Author: Niladri Sekhar Mondal
	Date: 13-02-23
*/
#include<stdio.h>
#include<stdlib.h>
struct node{
	int info;
	struct node *next;
};
void display(struct node *last)
{
	if(last==NULL)
	{
		printf("List is empty\n\n");
		return;
	}
	struct node *p = last->next;
	do
	{
		printf("%d ",p->info);
		p = p->next;
	}while(p!=last->next);
	printf("\n");
}
struct node *addtoempty(struct node *last, int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	last = tmp;
	last->next = last;
	return last;
}
struct node *addatbeg(struct node *last, int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->next = last->next;
	last->next = tmp;
	return last;
}
struct node *addatend(struct node *last, int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->next = last->next;
	last->next = tmp;
	last = tmp;
	return last;
}
struct node *addafter(struct node *last, int data, int item)
{
	struct node *tmp,*p;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	p = last->next;
	do
	{
		if(p->info == item)
		{
			tmp->next = p->next;
			p->next = tmp;
			if(p == last)
			{
				last = tmp;
			}
			return last;
		}
		p = p->next;
	}while(p!=last->next);
	printf("%d was not found on the list",item);
	return last;
}
/*struct node *addbefore(struct node *last,int data, int item)		//double check this function
{
	struct node *tmp,*p;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	p = last->next;
	do
	{
		if(p->next->info == item)
		{
			tmp->next = p->next;
			p->next = tmp;
		}
	}
}*/
struct node *del(struct node *last, int item)
{
	struct node *tmp,*p;
	if(last  == NULL)
	{
		printf("List is empty");
		return last;
	}
	if(last->next == NULL && last->next->info == item)
	{
		tmp = last;
		last = NULL;
		free(tmp);
		return last;
	}
	if(last->next->info == item)
	{
		tmp = last->next;
		last->next = tmp->next;
		free(tmp);
		return last;
	}
	p = last->next;
	while(p->next!=last)
	{
		if(p->next->info == item)
		{
			tmp=p->next;
			p->next = tmp->next;
			free(tmp);
			return last;
		}
		p=p->next;
	}
	if(last->info == item)
	{
		tmp=last;
		p->next = last->next;
		last = p;
		free(tmp);
		return last;
	}
	printf("%d is not found\n\n",item);
}
