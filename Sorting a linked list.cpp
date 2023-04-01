#include<stdio.h>
#include<stdlib.h>
struct node{
	int info;
	struct node *next;
};
struct node *addatbeg(struct node *start,int data)
{
	struct node *tmp;
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	tmp->next = start;
	start = tmp;
	return start;
}
struct node *addatend(struct node *start,int data)
{
	struct node *p,*tmp;
	p = start;
	while(p->next!=NULL)
	{
		p = p->next;
	}
	tmp = (struct node*)malloc(sizeof(struct node));
	tmp->info = data;
	p->next = tmp;
	tmp->next = NULL;
	return start;
}
void display(struct node *start)
{
	struct node *p;
	if(start == NULL)
	{
		printf("List is empty\n");
		return;
	}
	printf("Elements are: ");
	p = start;
	while(p!=NULL)
	{
		printf("%d ",p->info);
		p = p->next;
	}
	printf("\n");
}
struct node *create_list(struct node* start)
{
	int i,n;
	printf("Enter the number of nodes to be inserted: ");
	scanf("%d",&n);
	start = NULL;
	if(n==0)
	{
		return start;
	}
	int data;
	printf("Enter the value of the element: ");
	scanf("%d",&data);
	start = addatbeg(start,data);
	for(i = 2;i<=n;i++)
	{
		printf("Enter the value of the element: ");
		scanf("%d",&data);
		start = addatend(start,data);
	}
	return start;
}
void options()
{
	printf("1.Create List\n");
	printf("2.Display\n");
	printf("3.Selection Sort by exchanging Data\n");
	printf("4.Selection Sort by exchanging Links\n");
	printf("5.Bubble Sort by exchanging Data\n");
	printf("6.Bubble Sort by exchanging Links\n");
	printf("7.Show options\n");
	printf("8.Quit\n");
}
struct node *selection_sort_by_data(struct node *start)
{
	int tmp;
	struct node *p,*q;
	for(p=start;p->next!=NULL;p=p->next)
	{
		for(q=p->next;q!=NULL;q=q->next)
		{
			if(p->info>q->info)
			{
				tmp = p->info;
				p->info = q->info;
				q->info = tmp;
			}
		}
	}
	return start;
}
struct node *selection_sort_by_links(struct node *start)
{
	struct node *tmp,*p,*q,*r,*s;
	for(r=p=start;p->next!=NULL;r=p,p=p->next)
	{
		for(s=q=p->next;q!=NULL;s=q,q=q->next)
		{
			if(p->info>q->info)
			{
				tmp = p->next;
				p->next = q->next;
				q->next = tmp;
				if(p!=start)
				{
					r->next = q;
				}
				s->next = p;
				if(p==start)
				{
					start = q;
				}
				tmp = p;
				p = q;
				q = tmp;
			}
		}
	}
	return start;
}
struct node *bubble_sort_by_data(struct node *start)
{
	int tmp;
	struct node *p,*q,*end;
	for(end=NULL;end!=start->next;end=q)
	{
		for(p = start;p->next!=end;p = p->next)
		{
			q = p->next;
			if(p->info > q->info)
			{
				tmp = p->info;
				p->info = q->info;
				q->info = tmp;
			}
		}
	}
	return start;
}
struct node *bubble_sort_by_links(struct node *start)
{
	struct node *tmp,*r,*p,*q,*end;
	for(end=NULL;end!=start->next;end=q)
	{
		for(p = start;p->next!=end;p = p->next)
		{
			q = p->next;
			if(p->info > q->info)
			{
				p->next = q->next;
				q->next = p;
				if(p!=start)
				{
					r->next = q;
				}
				else
				{
					start = q;
				}
				tmp = p;
				p = q;
				q = tmp;
			}
		}
	}
	return start;
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
				selection_sort_by_data(start);
				break;
			case 4:
				selection_sort_by_links(start);
				break;
			case 5:
				bubble_sort_by_data(start);
				break;
			case 6:
				bubble_sort_by_links(start);
				break;
			case 10:
				options();
				break;	
			case 11:
				exit(1);
			default:
				printf("Invalid Input\n");	
		}
	}
	return 0;
}