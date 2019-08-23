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
