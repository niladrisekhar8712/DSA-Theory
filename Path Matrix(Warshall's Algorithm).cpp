/*
	Name: Path Matrix using Warshall's Algorithm 
	Author: NILADRI SEKHAR MONDAL
	Date: 24-05-23
*/
#include<iostream>
#define MAX 100
int adj[MAX][MAX];
int n;
using namespace std;
void create_graph()
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
void display(int mat[MAX][MAX])
{
	for(int i = 0;i<n;i++)
	{
		for(int j = 0;j<n;j++)
		{
			cout<<mat[i][j]<<" ";
		}
		cout<<endl;
	}
}
void warshallAlgorithm()
{
	int p[MAX][MAX];
	for(int i = 0;i<n;i++)
	{
		for(int j = 0;j<n;j++)
		{
			p[i][j] = adj[i][j];
		}
	}
	cout<<"The Adjacency Matrix(P_-1) is: "<<endl;
	display(p);
	cout<<endl;
	for(int k = 0;k<=n-1;k++)
	{
		for(int i = 0;i<n;i++)
		{
			for(int j = 0;j<n;j++)
			{
				if(p[i][j] == 1 ||(p[i][k] == 1 && p[k][j] == 1))
				{
					p[i][j] = 1;
				}
				else p[i][j] = 0;
			}
		}
		cout<<"The Matrix p_"<<k<<" is: "<<endl;
		display(p);
		cout<<endl;
	}
}
int main()
{
	create_graph();
	warshallAlgorithm();
	cout<<"This is the path matrix"<<endl;
	return 0;
}