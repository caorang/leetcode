package cn.hutool.jackie.algorithm.coupang.q0702;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* CallHandler represents the body of the program,
 * and all calls are funneled first through it.
 */
public class CallHandler {
    /* We have 3 levels of employees: respondents, managers, directors. */
    private final int LEVELS = 3;

    /* Initialize with 10 respondents, 4 managers, and 2 directors. */
    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;

    /* List of employees, by level.
     * employeeLevels[0] = respondents
     * employeeLevels[1] = managers
     * employeeLevels[2] = directors
     */
    List<List<Employee>> employeeLevels;

    /* queues for each call�s rank */
    Queue<Call> callQueue;

    public CallHandler() {
        employeeLevels = new ArrayList<List<Employee>>(LEVELS);
        callQueue = new ArrayDeque<>();

        // Create respondents.
        ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
        for (int k = 0; k < NUM_RESPONDENTS - 1; k++) {
            respondents.add(new Respondent(this, k));
        }
        employeeLevels.add(respondents);

        // Create managers.
        ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGERS);
        managers.add(new Manager(this, 101));
        employeeLevels.add(managers);

        // Create directors.
        ArrayList<Employee> directors = new ArrayList<Employee>(NUM_DIRECTORS);
        directors.add(new Director(this, 201));
        employeeLevels.add(directors);
    }

    /* Gets the first available employee who can handle this call. */
    public Employee getHandlerForCall(Call call) {
        for (int level = call.getRank().getValue(); level < LEVELS; level++) {
            List<Employee> employeeLevel = employeeLevels.get(level);
            for (Employee emp : employeeLevel) {
                if (emp.isFree()) {
                    return emp;
                }
            }
        }
        return null;
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    public void dispatchCall(Call call) {
        /* Try to route the call to an employee with minimal rank. */
        Employee emp = getHandlerForCall(call);
        if (emp != null) {
            emp.receiveCall(call);
            call.setHandler(emp);
        } else {
            /* Place the call into corresponding call queue according to its rank. */
            call.reply("Please wait for free employee to reply");
            callQueue.add(call);
            alertQueues();
        }
    }

    private void alertQueues() {
        System.out.println("CallCenter: queue status: " + callQueue);
    }

    /* An employee got free. Look for a waiting call that he/she can serve. Return true
     * if we were able to assign a call, false otherwise. */
    public boolean assignCall(Employee emp) {
        /* Check the queues, starting from the highest rank this employee can serve. */
        if (callQueue != null && !callQueue.isEmpty()) {
            this.dispatchCall(callQueue.poll());
            return true;
        }
        return false;
    }
}


