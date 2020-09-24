# Maintenance Group 5

## Review Rules

* All code being reviewed must pass the flake8 && ant checkstyle [(refer to: Setting up your development environment)](https://voc.readthedocs.io/en/latest/how-to/development-env.html)
* Code convention must be the same as already existing in the repository, specifically the other standard library classes and tests.
* All classes and methods being reviewed must have relevant documentation.
* Testing being reviewed must be passing when run.
* Code coverage for each test being reviewed must be at least 80% for code relevant to testing.
* Pull requests for reviewing must be made before 23:59 Sunday 27 September, 2020 (Uppsala time).
* Any number of pull requests can be made before this deadline, either:
    * Incremental, with separate reviews for each
    * Single, with a single review
* Each pull request must be made from:
    * SprintTwoTeam<team number>Development branch 
* Each pull request must be made to:
    * SprintTwoTeam<team number> branch
* Reviews for each pull request must be made no later than:
    * 23:59 (Uppsala time) on the day following the creation of the pull request.
* Any reply comments and/or fixes relative to a pull request review must be completed no later than 
    * If review made before the deadline: 
        * 23:59 (Uppsala time) 2 days following the creation of the pull request review
    * If review made after the deadline:
        * 23:59 (Uppsala time) 1 day following the creation of the pull request review
* Each reply comment and/or fix relative to a pull request review must also be reviewed with the same conditions as specified above - time dependent.
* All pull requests must be reviewed
    * at least once for initial pull request
        * with a comment to confirm that each above requirement is/isn't met
    * at least once for each additional comment in reply and/or fix - time dependent
        * with a comment to confirm that each above requirement is/isn't met
    * by any member(s) of a sub-team (or pair)
        * each member that does review will give an individual comment for that review
    * responsibility for pull request reviews being completed is on the whole team
        * if a pull request has no review or comment with time remaining, then all sub-team are responsible

## Further requirements

* Python v3.85 must be used for all python development
* Java v8 must be used for all java development
* Junit v5 must be used for all java testing
* JaCoCo v0.8.6 must be used for all java code coverage
