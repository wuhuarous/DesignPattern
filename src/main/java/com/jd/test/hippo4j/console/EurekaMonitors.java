package com.jd.test.hippo4j.console;/*
 * Copyright 2012 Netflix, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * The enum that encapsulates all statistics monitored by Eureka.
 *
 * <p>
 * Eureka Monitoring is done using <a href="https://github.com/Netflix/servo">Servo</a>. The
 * users who wants to take advantage of the monitoring should read up on
 * <tt>Servo</tt>
 * <p>
 *
 * @author Karthik Ranganathan, Greg Kim
 */
public enum EurekaMonitors {
    RENEW("renewCounter", "Number of total renews seen since startup"),
    CANCEL("cancelCounter", "Number of total cancels seen since startup"),
    GET_ALL_CACHE_MISS("getAllCacheMissCounter", "Number of total registry queries seen since startup"),
    GET_ALL_CACHE_MISS_DELTA("getAllCacheMissDeltaCounter",
            "Number of total registry queries for delta seen since startup"),
    GET_ALL_WITH_REMOTE_REGIONS_CACHE_MISS("getAllWithRemoteRegionCacheMissCounter",
            "Number of total registry with remote region queries seen since startup"),
    GET_ALL_WITH_REMOTE_REGIONS_CACHE_MISS_DELTA("getAllWithRemoteRegionCacheMissDeltaCounter",
            "Number of total registry queries for delta with remote region seen since startup"),
    GET_ALL_DELTA("getAllDeltaCounter", "Number of total deltas since startup"),
    GET_ALL_DELTA_WITH_REMOTE_REGIONS("getAllDeltaWithRemoteRegionCounter",
            "Number of total deltas with remote regions since startup"),
    GET_ALL("getAllCounter", "Number of total registry queries seen since startup"),
    GET_ALL_WITH_REMOTE_REGIONS("getAllWithRemoteRegionCounter",
            "Number of total registry queries with remote regions, seen since startup"),
    GET_APPLICATION("getApplicationCounter", "Number of total application queries seen since startup"),
    REGISTER("registerCounter", "Number of total registers seen since startup"),
    EXPIRED("expiredCounter", "Number of total expired leases since startup"),
    STATUS_UPDATE("statusUpdateCounter", "Number of total admin status updates since startup"),
    STATUS_OVERRIDE_DELETE("statusOverrideDeleteCounter", "Number of status override removals"),
    CANCEL_NOT_FOUND("cancelNotFoundCounter", "Number of total cancel requests on non-existing instance since startup"),
    RENEW_NOT_FOUND("renewNotFoundexpiredCounter", "Number of total renew on non-existing instance since startup"),
    REJECTED_REPLICATIONS("numOfRejectedReplications", "Number of replications rejected because of full queue"),
    FAILED_REPLICATIONS("numOfFailedReplications", "Number of failed replications - likely from timeouts"),
    RATE_LIMITED("numOfRateLimitedRequests", "Number of requests discarded by the rate limiter"),
    RATE_LIMITED_CANDIDATES("numOfRateLimitedRequestCandidates", "Number of requests that would be discarded if the rate limiter's throttling is activated"),
    RATE_LIMITED_FULL_FETCH("numOfRateLimitedFullFetchRequests", "Number of full registry fetch requests discarded by the rate limiter"),
    RATE_LIMITED_FULL_FETCH_CANDIDATES("numOfRateLimitedFullFetchRequestCandidates", "Number of full registry fetch requests that would be discarded if the rate limiter's throttling is activated");

    private final String name;

    private final String description;

    private EurekaMonitors(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private final AtomicLong counter = new AtomicLong();


    private Map<String, String> map = new ConcurrentHashMap<>();



    public void put(String key, String value) {
        map.put(key, value);
    }


    public String get(String key) {
        return map.get(key);
    }

    /**
     * Increment the counter for the given statistic.
     */
    public void increment() {
        increment(false);
    }

    /**
     * Increment the counter for the given statistic based on whether this is
     * because of replication from other eureka servers or it is a eureka client
     * initiated action.
     *
     * @param isReplication true if this a replication, false otherwise.
     */
    public void increment(boolean isReplication) {
        counter.incrementAndGet();

    }

    /**
     * Gets the statistic name of this monitor.
     *
     * @return the statistic name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the zone specific statistic name of this monitor. Applies only for
     * AWS cloud.
     *
     * @return the zone specific statistic name.
     */

    /**
     * Gets the description of this statistic means.
     *
     * @return the description of this statistic means.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the actual counter value for this statistic.
     *
     * @return the long value representing the number of times this statistic
     * has occurred.
     */
    public long getCount() {
        return counter.get();
    }

    /**
     * Gets the zone specific counter value for this statistic. This is
     * application only for AWS cloud environment.
     *
     * @return the long value representing the number of times this statistic
     *         has occurred.
     */

    /**
     * Register all statistics with <tt>Servo</tt>.
     */


}
