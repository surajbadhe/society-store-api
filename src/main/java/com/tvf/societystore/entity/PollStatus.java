package com.tvf.societystore.entity;

public enum PollStatus {
    /**
     * The poll is active and residents can vote.
     */
    OPEN,

    /**
     * The voting period is over, and the poll is now open for suppliers to bid on.
     */
    AWAITING_BIDS,

    /**
     * The poll is finished, either because a winning bid was selected or it was cancelled.
     */
    CLOSED
}
