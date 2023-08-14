#include<stdlib.h>
#include<stdio.h>
#include<string.h>

struct listElem
{
	struct listElem *prev;
	struct listElem *next;
	int value;
	int priority;
};

struct queue
{
	struct listElem *begin;
	int size;
};

void push(struct queue *queue, struct listElem *listElem)
{
	if (queue->size == 0)
	{
		queue->begin = listElem;
		queue->size++;
		return;
	}
	
	struct listElem *elem=queue->begin;
	while (elem->next != NULL)
	{
		elem = elem->next;
	}

	elem->next = listElem;
	listElem->prev = elem;
	listElem->next = NULL;
	queue->size++;
}

void pop(struct queue *queue)
{
	if (queue->size == 0)
	{
		return;
	}
	else if (queue->size == 1)
	{
		queue->size = 0;
		queue->begin = NULL;
		return;
	}
	else
	{
		queue->size--;
		queue->begin = queue->begin->next;
		queue->begin->prev = NULL;
		return;
	}
}

void print(struct queue *queue)
{
	struct listElem *elem = queue->begin;
	while (elem->next != NULL)
	{
		printf("Wartosc: %d Priorytet: %d \n", elem->value, elem->priority);
		elem = elem->next;
	}

	printf("Wartosc: %d Priorytet: %d \n", elem->value, elem->priority);
}

void insert(struct queue *queue, struct listElem *elem, int priority)
{
	//0->najni¿szy priorytet
	elem->priority = priority;
	struct listElem *temp = queue->begin;

	if (queue->size == 0)
	{
		push(queue, elem);
		return;
	}

	while (temp->next != NULL)
	{
		if (temp->priority < priority)
		{
			break;
		}
		temp = temp->next;
	}

	queue->size++;
	if (queue->begin == temp)
	{
		if (temp->priority < elem->priority)
		{
			queue->begin = elem;
			elem->next = temp;
			temp->prev = elem;
			elem->prev = NULL;

			return;
		}
		else
		{
			temp->next = elem;
			elem->prev = temp;
			elem->next = NULL;

			return;
		}
	}
	if (temp->next == NULL)
	{
		if (elem->priority > temp->priority)
		{
			struct listElem *tmp = temp->prev;

			tmp->next = elem;
			elem->prev = tmp;
			elem->next = temp;
			temp->prev = elem;

			return;
		}
		else
		{
			temp->next = elem;
			elem->next = NULL;
			elem->prev = temp;

			return;
		}
	}

	struct listElem *tmp = temp->prev;

	tmp->next = elem;
	elem->prev = tmp;
	elem->next = temp;
	temp->prev = elem;

	return;
}

void delete_by_priority(struct queue *queue, int priority)
{
	struct listElem *e = queue->begin;

	while (e->next != NULL)
	{
		if (e->priority == priority)
		{
			if (queue->begin == e)
			{
				queue->begin = e->next;
				e->next->prev = NULL;
			}
			else
			{
				struct listElem *tmp = e->prev;
				tmp->next = e->next;
				e->next->prev = tmp;	
			}
			queue->size--;
		}
		e = e->next;
	}
	if (e->priority == priority)
	{
		queue->size--;
		e->prev->next = NULL;
	}
	return;
}

struct queue* merege_two(struct queue* q1, struct queue* q2)
{
	struct listElem* tmp1 = q1->begin;
	struct listElem* tmp2 = q2->begin;
	struct queue* merged = calloc(1, sizeof(struct queue));

	while (tmp1->next !=NULL)
	{
		struct listElem* tmp3 = calloc(1, sizeof(struct listElem));
		tmp3->priority = tmp1->priority;
		tmp3->value = tmp1->value;
		insert(merged, tmp3, tmp3->priority);
		tmp1 = tmp1->next;
	}
	while (tmp2->next != NULL)
	{
		struct listElem* tmp3 = calloc(1, sizeof(struct listElem));
		tmp3->priority = tmp2->priority;
		tmp3->value = tmp2->value;
		insert(merged, tmp3, tmp3->priority);
		tmp2 = tmp2->next;
	}

	struct listElem* tmp3 = calloc(1, sizeof(struct listElem));
	tmp3->priority = tmp1->priority;
	tmp3->value = tmp1->value;
	insert(merged, tmp3, tmp3->priority);

	struct listElem* tmp4 = calloc(1, sizeof(struct listElem));
	tmp4->priority = tmp2->priority;
	tmp4->value = tmp2->value;
	insert(merged, tmp4, tmp4->priority);

	return merged;
}

int main()
{
	struct queue* q1 = calloc(1,sizeof(struct queue));
	struct queue* q2 = calloc(1,sizeof(struct queue));
	struct queue* q3 = calloc(1,sizeof(struct queue));

	struct listElem* elem = calloc(1,sizeof(struct listElem));
	struct listElem* elem1 = calloc(1,sizeof(struct listElem));
	struct listElem* elem2 = calloc(1,sizeof(struct listElem));

	struct listElem* elem3 = calloc(1,sizeof(struct listElem));
	struct listElem* elem4 = calloc(1,sizeof(struct listElem));
	struct listElem* elem5 = calloc(1,sizeof(struct listElem));

	elem->priority = 2;
	elem->value = 4;

	elem1->priority = 0;
	elem1->value = 2;

	elem2->priority = 1;
	elem2->value = 2;

	elem3->priority = 5;
	elem3->value = 1;

	elem4->priority = 3;
	elem4->value = 8;

	elem5->priority = 0;
	elem5->value = 10;

	insert(q1, elem, 4);
	insert(q1, elem1, 4);
	//pop(q1);
	insert(q1, elem2, 1);
	//delete_by_priority(q1, 4);
	print(q1);
	printf("\n");

	insert(q2, elem3, 5);
	insert(q2, elem4, 8);
	insert(q2, elem5, 0);
	print(q2);
	printf("\n");
	printf("-----------------------------");
	printf("\n");

	q3 = merege_two(q1, q2);
	print(q3);
	printf("\n");
  //print(q1);
  //print(q2);

	//delete_by_priority(q1, 4);
	//print(q1);

	return 0;
}