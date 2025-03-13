# bc2412_sb

## Basic Concept/Skills
- 1. Spring Context -> Store Beans
 - Who create Bean? Before server start, "Spring Manager" manages bean cycle
 - Bean Cycle: @SpringBootApplication -> @ComponentScan, i.e. Find any class contain
 @Controller, @Service, @Configuration, @Repository
 - "Spring Manager" creates objects for the above classes, put them into Spring Context(Beans)
- 2. Get Beans from Context(resolve Dependencies between beans)
  - Autowired on Class Attribute (Field Injection)
    - "Spring Manager" resolve this dependency by finding an appropriate object fit into the attribute
    - @Autowired on Construtor(Constructor Injection)
- 3. Flow
  - Controller Bean always @Autowired Service Bean
  - Service Bean always @Autowired Repostitory Bean 
  - if "Spreing Manager" cannot find any dependency, server start will fail.
- 4. RESTful API (Get/Post/Delete/Put/Patch)
  - GET: without create, update or delete on resource 
  - POST: Create resource ONLY.
  - DELETE: Delete resource ONLY(by id, or other resource attribute)
  - PUT: Make sure target resource already exists(Find by id). 
  Then replace the resource by the new resource
  - PATCH: Make sure target resource already exists (Find by id). Then revise the target object attribute, but not replace the object

## Srping Boot Project Development 
- Create project by VScode. Add dependencies for your scenario.
- After project creation, restart the VScode 
- Check pom.xml and applocation.yml
- If you need add/remove dependency , restart VScode.
- Create controller folder
  - Inside the folder, create interface(xxxx Operation.java)
  - Create impl folder, create implementation for the interface
- Create service folder
  - Inside the folder, create interface(xxxx Service.java)
  - Create impl folder, create implementation for the interface
- When Controller @Autowired Service, remember to use service interface, but not implementation class
- test "mvn clean install" -> include server start 
  ## Exception

  ## Week10/11
  Spring boot: 
  - RESTful API (Controller)
  - Data (Repository + Entity)
  - Function (Service)
  - Additional Beans(Configuration)
  - Invoke External API (RestTemplate)
  - Data Transfer Object (DTO) x Mapper
  - Read Custome Variable from yml (@Value) -> e.g:Domain 
  - GlobalExceptionHandler
  - ApiResp.class (enum SysCode, BusinessExcpetion.class)