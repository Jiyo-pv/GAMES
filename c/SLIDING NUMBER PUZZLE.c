#include<stdio.h>
#include<conio.h>
#include<string.h>
void swap(int a[4][4],int pos_r,int pos_c,int row,int col)
{
	int temp=a[row][col];
	a[row][col]=a[pos_r][pos_c];
	a[pos_r][pos_c]=temp;
}
int check_status(int a[4][4])
{
	int i,j,status=1,flag=1;
	for(i=0;i<4;i++)
	{
	    for(j=0;j<4;j++)
	    {
		if(status!=a[i][j])
		{
			flag=0;
		}
		status=(status+1)%16;
	    }
	}
	return flag;
}
void main()
{
	int a[4][4];
	int n;
	int winner;
	int player;
	int move[10]={0};
	char name[10][10];
	int easy[4][4]={{1,2,7,3},{5,6,11,4},{9,10,15,8},{13,14,12,0}};
	int normal[4][4]={{1,2,3,4},{5,6,8,7},{9,10,12,11},{14,15,13,0}};
	int hard[4][4]={{5,9,11,10},{13,6,2,3},{1,7,14,4},{8,12,15,0}};
	char ch;
	int pos_r,i,j,pos_c,mode;
	clrscr();
	printf("\t\tSLIDING PUZZLE\nchoose difficulty\n1.easy \n2.normal \n3.hard\n");
	printf("->");
	scanf("%d",&mode);
	printf("enter no of players:");
	scanf("%d",&n);
	for(i=0;i<n;i++)
	{
		printf("enter name of player %d:",i);
		scanf("%s",&name[i]);
	}
	for(player=0;player<n;player++)
	{       pos_r=3,pos_c=3;
		if(mode==1)
		{  memcpy(a,easy,sizeof(easy));
		}
		else if(mode==2)
		{
			memcpy(a,normal,sizeof(normal));
		}
		else
		{
			memcpy(a,hard,sizeof(hard));
		}
		do
		{
			clrscr();
			printf("\t\tSLIDING PUZZLE\nplayer name:%s\n",name[player]);
			for(i=0;i<4;i++)
			{
				for(j=0;j<4;j++)
				{
					if(a[i][j]==0)
					{
						printf("|0|\t");
					}
					else
					{
						printf("%d\t",a[i][j]);
					}
				}
				printf("\n");
			}
			printf("w)up 	s)down	 d)right    a)left   0)exit	\n->");
			ch=getch();
			switch(ch)
			{
				case 'w':
					if(pos_r-1!=-1)
					{
						swap(a,pos_r,pos_c,pos_r-1,pos_c);
						pos_r--;
						move[player]+=1;
					}
					if(check_status(a))
					{
					clrscr();
					break;
					}
					break;
				case 's':
					if(pos_r+1!=4)
					{
						swap(a,pos_r,pos_c,pos_r+1,pos_c);
						pos_r++;
						move[player]+=1;
						if(check_status(a))
						{
							clrscr();
							break;
						}
					}
					break;
				case 'a':
					if(pos_c-1!=-1)
					{
						swap(a,pos_r,pos_c,pos_r,pos_c-1);
						pos_c--;
						move[player]+=1;
						if(check_status(a))
						{
							clrscr();
							break;
						}
					}
					break;
				case 'd':
					if(pos_c+1!=4)
					{
						swap(a,pos_r,pos_c,pos_r,pos_c+1);
						pos_c++;
						move[player]+=1;
						if(check_status(a))
						{
						clrscr();
						break;
						}
					}
					break;
				case '0':
					clrscr();
					printf("aborted game");
					getch();
					return;
				default:
					continue;

			}
			if(check_status(a))
			{
				clrscr();
				break;
			}

		}while(ch!='0');
	}
	clrscr();
	printf("_____________________________\n   SCORE BOARD\n-----------------------------\nplayer_name     moves\n_____________________________\n");
	winner=0;
	for(i=1;i<n;i++)
	{
		 if(move[i]<move[winner])  winner=i;
	}
	for(i=0;i<n;i++)
	{
		printf("%s\t     %d moves%s\n",name[i],move[i],(move[i]==move[winner])?"  (winner)":" ");
	}
	getch();
}
