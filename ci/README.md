# Concourse integration 
Curstomize
Steps for managing a simplified Concourse pipeline.

<b>login to concourse</b>
```
fly -t <team ci name> login -c http://concourse.sys.dev.gsdcf.manulife.com -n <team name> -u <team account> -p <team pwd>
```
<br/>

<b>update pipeline</b>
```
fly -t <team ci name> set-pipeline -c <pipeline YAML> -p <app specific ci> -n -l <credential config YAML>

```
<br/>
<b>execute pipeline</b>
```
fly -t <team ci name> execute --config tasks\assemble.yml
```
<br/>