/*
	Name: DIRECTED GRAPH OPERATIONS USING ADJACENCY MATRIX
	Author: NILADRI SEKHAR MONDAL
	Date: 04-05-23
*/
#include<iostream>
using namespace std;
#define MAX 100
int adj[MAX][MAX];
int n;

void create_matrix()
{
	int i,origin,destin,max_edge;
	cout<<"Enter no of vertices: ";
	cin>>n;
	max_edge = n*(n-1);
	for(i = 1;i<=max_edge;i++)
	{
		cout<<"Enter edge(-1 -1 to quit) "<<i<<": ";
		cin>>origin>>destin;
		if(origin == -1 || destin == -1)
		{
			break;
		}
		else if(origin>=n || destin>=n || origin<0 || destin<0)
		{
			cout<<"Invalid edge"<<endl;
			i--;
		}
		else
		{
			adj[origin][destin] = 1;
		}
	}
}
void insert(int origin, int destin)
{
	if(origin>=n || origin<0){
		cout<<"Invalid origin vertex"<<endl;
		return;
	}
	if(destin>=n || destin<0){
		cout<<"Invalid destination vertex"<<endl;
		return;
	}
	adj[origin][destin] = 1;
}
void del(int origin, int destin)
{
	if(origin>=n || origin<0 || destin>=n || destin<0 || adj[origin][destin]==0){
		cout<<"The edge does not exist"<<endl;
		return;
	}
	adj[origin][destin] = 0;
}
void display()
{
	int i, j;
	for(i = 0;i<n;i++)
	{
		for(j = 0;j<n;j++)
		{
			cout<<adj[i][j]<<" ";
		}
		cout<<endl;
	}
}
void options()
{
	cout<<"1. Insert an edge"<<endl;
	cout<<"2. Delete an edge"<<endl;
	cout<<"3. Display"<<endl;
	cout<<"4. Show Options"<<endl;
	cout<<"5. Exit"<<endl;
}
int main(){
	int choice, destin,origin;
	create_matrix();
	options();
	while(1)
	{
		cout<<"Enter your choice: ";
		cin>>choice;
		switch(choice)
		{
			case 1:
				cout<<"Enter an edge to be inserted: ";
				cin>>origin>>destin;
				insert(origin,destin);
				break;
			case 2:
				cout<<"Enter an edge to be deleted: ";
				cin>>origin>>destin;
				del(origin,destin);
				break;
			case 3:
				display();
				break;
			case 4:
				options();
				break;
			case 5:
				exit(1);
			default:
				cout<<"Invalid Input"<<endl;
		}
	}
}
