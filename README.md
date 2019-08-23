@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<BeanRulesGamification> getPetById(@PathVariable("id") ObjectId id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("team").in(true));
		//query.addCriteria(Criteria.where("individual").in(false));
	  return mongoTemplate.find(query,BeanRulesGamification.class);@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<BeanRulesGamification> getPetById(@PathVariable("id") ObjectId id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("team").in(true));
		//query.addCriteria(Criteria.where("individual").in(false));
	  return mongoTemplate.find(query,BeanRulesGamification.class);
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BeanRulesGamification createGame(@Valid @RequestBody BeanRulesGamification game) {
		System.out.println("Hemanth");
		Query query = new Query();
		query.addCriteria(Criteria.where("team").in(true));
		mongoTemplate.findOne(query, BeanRulesGamification.class);
        mongoTemplate.save(game);
	  return game;
