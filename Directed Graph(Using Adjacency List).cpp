/*
	Name: DIRECTED GRAPH OPERATIONS USING ADJACENCY LIST 
	Author: NILADRI SEKHAR MONDAL
	Date: 05-05-23
*/
#include<iostream>
using namespace std;
class Edge;
class Vertex{
	public:
		int info;
		Edge *firstEdge;
		Vertex *nextVertex;
};
class Edge{
	public:
		Vertex *destinationVertex;
		Edge *nextEdge;
};
Vertex *start = NULL;
Vertex *findVertex(int u)
{
	Vertex *ptr,*loc;
	ptr = start;
	while(ptr!=NULL)
	{
		if(ptr->info == u)
		{
			return ptr;
		}
		ptr = ptr->nextVertex;
	}
	return NULL;
}
void insertVertex(int u)
{
	Vertex *ptr,*tmp;
	tmp = new Vertex();
	tmp->info = u;
	tmp->firstEdge = NULL;
	tmp->nextVertex = NULL;
	if(start == NULL)
	{
		start = tmp;
		return;
	}
	ptr = start;
	while(ptr->nextVertex != NULL)
	{
		ptr = ptr->nextVertex;
	}
	ptr->nextVertex = tmp;
}
void deleteVertex(int u)
{
	Vertex *tmp,*q;
	Edge *temporary, *p;
	if(start == NULL)
	{
		cout<<"No vertices to delete"<<endl;
		return;
	}
	if(start->info == u)
	{
		tmp = start;
		start = start->nextVertex;
	}
	else
	{
		q = start;
		while(q->nextVertex != NULL)
		{
			if(q->nextVertex->info == u)
			{
				break;
			}
			q = q->nextVertex;
		}
		if(q->nextVertex == NULL)
		{
			cout<<"Vertex not found"<<endl;
			return;
		}
		else
		{
			tmp = q->nextVertex;
			q->nextVertex = tmp->nextVertex;
		}
	}
	p = tmp->firstEdge;
	while(p!=NULL)
	{
		temporary = p;
		p = p->nextEdge;
		free(temporary);
	}
	free(tmp);
}
void deleteIncomingEdges(int u)
{
	Vertex *ptr;
	Edge *temporary,*q;
	ptr = start;
	while(ptr!=NULL)
	{
		if(ptr->firstEdge == NULL)
		{
			ptr = ptr->nextVertex;
			continue;
		}
		if(ptr->firstEdge->destinationVertex->info == u)
		{
			temporary = ptr->firstEdge;
			ptr->firstEdge = temporary->nextEdge;
			free(temporary);
			continue;
		}
		q = ptr->firstEdge;
		while(q->nextEdge!=NULL)
		{
			if(q->nextEdge->destinationVertex->info == u)
			{
				temporary = q->nextEdge;
				q->nextEdge = temporary->nextEdge;
				free(temporary);
				continue;
			}
			q = q->nextEdge;
		}
		ptr = ptr->nextVertex;
	}
}
void insertEdge(int u, int v)
{
	Vertex *locu, *locv;
	locu = findVertex(u);
	locv = findVertex(v);
	if(locu == NULL)
	{
		cout<<"Origin vertex does not exist"<<endl;
		return;
	}
	if(locv == NULL)
	{
		cout<<"Destination vertex does not exist"<<endl;
		return;
	}
	Edge *temporary = new Edge();
	temporary->destinationVertex = locv;
	temporary->nextEdge = NULL;
	Edge *p = locu->firstEdge;
	if(locu->firstEdge == NULL)
	{
		locu->firstEdge = temporary;
		return;
	}
	while(p->nextEdge!=NULL)
	{
		p = p->nextEdge;
	}
	p->nextEdge = temporary;
}
void deleteEdge(int u,int v)
{
	Edge *tmp, *p;
	Vertex *locu = findVertex(u);
	if(locu == NULL)
	{
		cout<<"Origin vertex does not exist"<<endl;
		return;
	}
	if(locu->firstEdge == NULL)
	{
		cout<<"Edge not present"<<endl;
		return;
	}
	if(locu->firstEdge->destinationVertex->info == v)
	{
		tmp = locu->firstEdge;
		locu->firstEdge = tmp->nextEdge;
		free(tmp);
		return;
	}
	p = locu->firstEdge;
	while(p->nextEdge!=NULL)
	{
		if(p->nextEdge->destinationVertex->info == v)
		{
			tmp = p->nextEdge;
			p->nextEdge = tmp->nextEdge;
			free(tmp);
			return;
		}
		p = p->nextEdge;
	}
	cout<<"This edge is not present in the graph"<<endl;
}
void display()
{
	Vertex *ptr;
	Edge *q;
	ptr = start;
	while(ptr!=NULL)
	{
		cout<<ptr->info<<" -> ";
		q = ptr->firstEdge;
		while(q!=NULL)
		{
			cout<<" "<<q->destinationVertex->info;
			q = q->nextEdge;
		}
		cout<<endl;
		ptr = ptr->nextVertex;
	}
}
void options()
{
	cout<<"1. Insert a Vertex"<<endl;
	cout<<"2. Insert an Edge"<<endl;
	cout<<"3. Delete a Vertex"<<endl;
	cout<<"4. Delete an Edge"<<endl;
	cout<<"5. Display"<<endl;
	cout<<"6. Show Options"<<endl;
	cout<<"7. Exit"<<endl;
}
int main()
{
	int choice, u, origin, destin;
	options();
	while(true)
	{
		cout<<"Enter your choice: ";
		cin>>choice;
		fflush(stdin);
		switch(choice)
		{
			case 1:
				cout<<"Enter a vertex to be inserted: ";
				cin>>u;
				insertVertex(u);
				break;
			case 2:
				cout<<"Enter an edge to be inserted: ";
				cin>>origin>>destin;
				insertEdge(origin,destin);
				break;
			case 3:
				cout<<"Enter a vertex to be deleted: ";
				cin>>u;
				deleteIncomingEdges(u);
				deleteVertex(u);
				break;
			case 4:
				cout<<"Enter an edge to be deleted: ";
				cin>>origin>>destin;
				deleteEdge(origin,destin);
				break;
			case 5:
				display();
				break;
			case 6:
				options();
				break;
			case 7:
				exit(1);
			default:
				cout<<"Invalid Input"<<endl;
		}
	}
}