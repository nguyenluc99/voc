
#!/bin/bash

#
# Set constants.
#
junit_platform_version='1.7.0'
ant_version='1.10.7'
ant_folder="apache-ant-${ant_version}"
ant_archive="${ant_folder}-bin.tar.gz"

#
# Finally, let Ant do its work...
#
ANT_HOME=${ant_folder} "./${ant_folder}/bin/ant" java-test

cmd /k
