/*
	Name: Path Matrix using Matrix Multiplication
	Author: NILADRI SEKHAR MONDAL
	Date: 18-05-23
*/
#include<iostream>
#define MAX 100
using namespace std;
int adj[MAX][MAX];
int n;
void createGraph() {
	cout<<"Enter the no of vertices of the graph: ";
	cin>>n;
	int origin,destin;
	int maxEdges = n*(n-1);
	for(int i = 1;i<=maxEdges;i++){
		cout<<"Enter an edge(press -1 -1 to exit): ";
		cin>>origin>>destin;
		if(origin == -1 && destin == -1)
		{
			break;
		}
		if(origin>=n || origin<0 || destin>=n || destin<0) // vertices only have values in range [0,n-1]
		{
			cout<<"Invalid Edge"<<endl;
			i--;
		}
		else
		{
			adj[origin][destin] = 1;
		}
	}
}
void multiply(int mat1[MAX][MAX],int mat2[MAX][MAX],int mat3[MAX][MAX])
{
	int i,j,k;
	for(i = 0;i<n;i++)
	{
		for(j = 0;j<n;j++)
		{
			mat3[i][j] = 0;
			for(k=0;k<n;k++)
			{
				mat3[i][j] += mat1[i][k]*mat2[k][j];
			}
		}
	}
}
void powMatrix(int adjp[MAX][MAX],int p)
{
	int tmp[MAX][MAX];
	int i,j,k;
	for(i = 0;i<n;i++)
	{
		for(j = 0;j<n;j++)
		{
			adjp[i][j] = adj[i][j];
		}
	}
	for(k = 1;k<p;k++)
	{
		multiply(adjp,adj,tmp);
		for(i = 0;i<n;i++)
		{
			for(j = 0;j<n;j++)
			{
				adjp[i][j] = tmp[i][j];
			}
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
	cout<<endl;
}
int main()
{
	int path[MAX][MAX];
	int x[MAX][MAX];
	createGraph();
	cout<<"Adjacency Matrix: "<<endl;
	display(adj);
	for(int i = 0;i<n;i++)
	{
		for(int j = 0;j<n;j++)
		{
			x[i][j] = 0;
		}
	}
	int adjp[MAX][MAX];
	for(int p = 1;p<=n;p++)
	{
		powMatrix(adjp,p);
		cout<<"Adjacency Matrix raised to the power "<<p<<": "<<endl;
		display(adjp);
		for(int i = 0;i<n;i++)
		{
			for(int j = 0;j<n;j++)
			{
				x[i][j] += adjp[i][j];
			}
		}
	}
	cout<<"The Matrix x is: "<<endl;
	display(x);
	for(int i = 0;i<n;i++)
	{
		for(int j = 0;j<n;j++)
		{
			if(x[i][j] == 0)
			{
				path[i][j] = 0;
			}
			else
			{
				path[i][j] = 1;
			}
		}
	}
	cout<<"The path matrix is: "<<endl;
	display(path);
	return 0;
}