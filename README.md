Ebean Agent Initializer
===

The purpose of this Bathe Booter Initializer is that when it runs, it will check to see if there is a System property
webapp.agents - which if it exists, it will be split into bits and the classpath will be searched for classpath elements
 that contain that name.

If there is a webapp.agent.[token]=[something] then it will pass the matching system property as a parameter to the Agent loader.