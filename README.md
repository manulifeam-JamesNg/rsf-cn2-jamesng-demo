## Document your service here!

-	Ensure that your code has been checked into git before attempting build

```	
cd <project>
git init
git add --all
git commit -m "Initial Commit"
git remote add origin <location/project>.git
git push -u origin master
```
	
-	Build, install and make site

```
mvnw clean install site
```
-	Check target/site/pmd.html for issues

-	Run locally

```
mvnw spring-boot:run -Dspring.profiles.active=dev
```

-	Deploy cloud

```
cf push -f manifest-dev.yml
```

