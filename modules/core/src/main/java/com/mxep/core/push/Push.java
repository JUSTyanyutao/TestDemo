package com.mxep.core.push;

public interface Push<P, R> {

	 R sendNotifaction(P p);

	 R sendBatchNotifaction(P p);
}
