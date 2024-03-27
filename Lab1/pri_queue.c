#include <stdlib.h>
#include <stdio.h>
#include "pri_queue.h"
/** @file pri_queue.c */
static Node_ptr_t head = NULL;
/**
 * Insert a Node into a priority queue.
 * @param priority
 * @param data
 * @author Sarim Shahwar
 */
void PQ_insert(int priority, char * data) {
 //FIX THIS
    Node_ptr_t new_node = (Node_ptr_t)malloc(sizeof(Node_t));
    if (new_node == NULL) {
        fprintf(stderr, "Failed Memory allocation\n");
        exit(EXIT_FAILURE);
    }
    new_node->priority = priority;
    new_node->data = data;
    new_node->next= NULL;
    //insertion beginning 
    if(head == NULL || priority > head->priority){
        new_node->next = head;
        head = new_node;
                
    } else { //insertion middle or end
        Node_ptr_t current = head;
        while (current->next != NULL && priority <= current->next->priority) {
            current = current->next;
        }
        new_node->next = current->next;
        current->next = new_node;

    }
}
/**
 * Delete and return the node with the highest priority.
 * @return The highest priority Node.
 */
Node_ptr_t PQ_delete() {
   if (head == NULL) {
        return NULL; // Queue is empty - NULL = Empty
    }

    Node_ptr_t deleted_node = head;
    head = head->next;
    deleted_node->next = NULL; // Remove the node from the list

    return deleted_node;
}

/**
 * Do NOT modify this function.
 * @return A pointer to the head of the list.  (NULL if list is empty.)
 */
Node_ptr_t PQ_get_head() {
    return head;
}

/**
 * Do NOT modify this function.
 * @return the number of items in the queue
 */
int PQ_get_size() {
    int size = 0;
    Node_ptr_t tmp;
    for(tmp = head; tmp != NULL; tmp = tmp->next, size++)
        ;
    return size;
}


