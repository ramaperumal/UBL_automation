
import groovy.transform.Field
import hudson.AbortException

@Field final String gitRepoUrl = 'https://ghe.aa.com/aot-foct/DOTC_RAS_UI_Automation'
@Field final String gitRepoDisplayName = 'DOTC_RAS_UI_Automation'


properties properties: [
        disableConcurrentBuilds(),
        [$class: 'GithubProjectProperty', displayName: gitRepoDisplayName, projectUrlStr: gitRepoUrl],
        buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')),
       parameters([
			choice(name: 'Environment', defaultValue: 'QA', choices: ['QA CLOUD', 'QA', 'UAT', 'STAGE', 'DEV' ], description: 'Environment'),
			string(name: 'Tag', defaultValue: '@US1272281', description: 'Test teg for execution (like: @Smoke,@US12345)'),
			string(name: 'SauceUsername', defaultValue: '', description: '(optional) Leaving empty: will run on default id'),
			string(name: 'SauceKey', defaultValue: '', description: '(optional) Saucelabs Key!'),
			string(name: 'EmailList', defaultValue: '', description: 'Report will be sent to the addresses!')
        ])
]

notify = new com.aa.jenkins.pipeline.notify()
structure = new com.aa.jenkins.pipeline.structure()
report = new com.aa.jenkins.pipeline.report()

@Field final String settingsFilePath = ''
@Field String branch = null
Map<String, Object> stageReportParams
@Field String emailList = ''

@Field String testResults
@Field String tmp
@Field String emailableTestResults

@Field String stageName
timestamps {
    node('Builder') {
        stageName = 'test'
        stage(stageName) {
//            sh 'pwd ; ls -al ; env | sort' // debug

//            branch = structure.gitBranch()
		environment {
		                SauceUsername 
		                SauceKey 
		                Endpoint
		            }
            branch = structure.gitBranchFromScm(scm)
            env.branch=branch
            echo "branch=${branch}"
			tmp="${params.EmailList}"
			echo "EmailList: ${params.EmailList}"
            //notify.notifySlack("START")

            if (env.EMAIL_LIST) {
                emailList = env.EMAIL_LIST
            }

            if (tmp!=null && tmp.length()>1) {
                emailList = tmp
            }
			tmp="${params.SauceUsername}"
            if (tmp!=null && tmp.length()>1) {
                echo "setting the env SauceUsername to: "+tmp
                env.SauceUsername = tmp
                }
			tmp="${params.SauceKey}"
            if (tmp!=null && tmp.length()>1) {
                env.SauceKey = tmp
            }
			tmp="${params.Endpoint}"
             if (tmp!=null && tmp.length()>1) {
            	echo "setting the env Endpoint to:"+ tmp
                env.Endpoint = tmp
            }
			
            structure.withNotification(to: emailList, includeDevelopers: false, includeCulprits: false, stageName: stageName) {
                checkout scm

                stageReportParams = report.stageCompleted(stageName: "git", branch: branch, gitRepoUrl: gitRepoUrl)

                // configure settings.xml to be used for this build, prepare artifact model for publish to Nexus
                withCredentials([usernamePassword(credentialsId: 'CICD-Enterprise-Nexus3-Deployer', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USER')]) {
                    loadSettingsXml(user: NEXUS_USER, password: NEXUS_PASSWORD, useEnterpriseNexus: true)
                    settingsFilePath = "${env.WORKSPACE}/.settings.xml"
                }

                // Actual test done by maven or replace with other testing tools
                echo "Tag is: ${params.Tag} "
                testResults= "Branch:  ${branch} \r\n  Tage: ${params.Tag} \r\n endpoint: ${params.Endpoint} "
                singleJob(stageName, "clean test -U -P Smoke -Dsonar.skip=true -Dcucumber.options='--tags ${params.Tag}'", '')  //'--tags @US1276168'

                // Report on test status
                //archiveArtifacts "ExtentReports"
                archiveArtifacts "ExtentReports/ExtentReport.html"
                //archiveArtifacts "test-output/emailable-report.html"
                 
				//emailableTestResults = readFile file: 'ExtentReports/ExtentReport.html'
				//publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: "ExtentReports/*.*", reportFiles: "ExtentReport.html", reportName: 'Automation Execution Report Summary', reportTitles: ''])
                report.stageCompleted(stageName: "itest", params: stageReportParams)
            }
        }

        stageName = 'notifications'
        stage(stageName) {
            report.buildCompleted(stageReportParams, currentBuild.startTimeInMillis, System.currentTimeMillis(), true)

            String currentBuildResult = "${currentBuild.result ?: 'SUCCESS'}"
            echo "${currentBuildResult} : Job '${env.JOB_NAME} [${env.BUILD_NUMBER} ${branch}]'"
            //tmp= 'target/cucumber-results-feature-overview.html';
			//try{
				//testResults += readFile file: tmp
				//notify.notifyWithCurrentBuildResult([to: emailList, includeDevelopers: true, bodyMsg: testResults], currentBuildResult)
			//}catch(Exception e){
				//try{
					//echo 'NO report at: '+ tmp
					//tmp= 'target/cucumber-results-agg-test-results.html'
					//testResults += readFile file: tmp
					//notify.notifyWithCurrentBuildResult([to: emailList, includeDevelopers: true, bodyMsg: testResults], currentBuildResult)
				//}catch(Exception ex){
					//try{
						//echo 'NO report at: '+ tmp
						//tmp = 'Results/Run_*_AM/Smoke/index.html'
						//testResults += readFile file: tmp
						//notify.notifyWithCurrentBuildResult([to: emailList, includeDevelopers: true, bodyMsg: testResults], currentBuildResult)
					//}catch(Exception ee){
							//echo 'NO report at: '+ tmp
							//testResults += ': nill / NO report generated at Results, and tareget directory!'
							//notify.notifyWithCurrentBuildResult([to: emailList, includeDevelopers: true, bodyMsg: testResults], currentBuildResult)
						//}
				//}
			//}
			//echo 'archiveArtifacts-ing!'
           // tmp= 'target/cucumber-results-feature-overview.html';
			//try{
				//archiveArtifacts tmp
			//}catch(Exception e){
				//try{
					//echo 'NO archiveArtifacts at: '+ tmp
					//tmp= 'target/cucumber-results-agg-test-results.html'
					//archiveArtifacts tmp
				//}catch(Exception ex){
					//try{
						//echo 'NO archiveArtifacts at: '+ tmp
						//tmp = 'Results/Run_*_AM/Smoke/index.html'
						//archiveArtifacts tmp
					//}catch(Exception ee){
							//echo 'NO archiveArtifacts at: '+ tmp
						//}
				//}
			//}
			
			
			
			
          //  echo "Status mail: ${currentBuild.result}"
		// publishHTML(target: [allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'ExtentReports', reportFiles: '*', reportName: "ExtentReport"])
        //  emailext attachmentsPattern: 'target/*',body: "Check CloudBees Jenkins console output at ${env.BUILD_URL} to view the results.<br></br> ",
         // 	 mimeType: 'text/html', subject: "${env.JOB_NAME}", to:'Mohammad.Mohammedpouraghdam@aa.com'
         // echo "Status mail: ${currentBuild.result}"
		 // emailext attachLog: true, body: "Check console output at ${env.BUILD_URL} to view the results.<br></br> ", mimeType: 'text/html', subject: "${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${currentBuild.result}", to:'Mohammad.Mohammedpouraghdam@aa.com'                    
         // publishHTML(target: [allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'ExtentReports/', reportFiles: '*', reportName: "Extent_Report"])
         // emailext attachmentsPattern: 'target/surefire-reports/html/*',body: "Check CloudBees Jenkins console output at ${env.BUILD_URL} to view the results.<br></br> ", mimeType: 'text/html', subject: "${env.JOB_NAME}", to:'Mohammad.Mohammedpouraghdam@aa.com'
        }
    }
}

// ---------------------------------------------------------------------------------------------------------

def singleJob(String job, String targets, String sonarTargets) {
    if (!targets) {
        targets = 'clean test'
    }

    echo job
    structure.withMavenAndJava(java: 'java8', maven: 'apache-maven-3.3.9', mavenSettingsFilePath: settingsFilePath) {
        sh('mvn --batch-mode ' + targets)
        if (structure.isMasterBranch() && sonarTargets) {
            withSonarQubeEnv('CICD') {
                try {
                    sh 'mvn --batch-mode ' + sonarTargets
                } catch (AbortException e) {
                    println "WARNING: build status UNSTABLE due to error executing SonarQube scan: ${e.message}"
                    currentBuild.result = 'UNSTABLE' // do not fail build
                }
            }
        }
    }
}