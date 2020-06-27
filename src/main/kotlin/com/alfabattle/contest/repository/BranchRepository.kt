package com.alfabattle.contest.repository

import com.alfabattle.contest.model.Branch
import org.springframework.data.repository.CrudRepository

interface BranchRepository: CrudRepository<Branch, Int>
