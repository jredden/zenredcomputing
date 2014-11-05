package com.zenred.zenredcomputing.vizualization;

import java.util.List;

import com.zenred.zenredcomputing.domain.DateOperation;
import com.zenred.zenredcomputing.domain.DomainTransfer;
import com.zenred.zenredcomputing.domain.Posts;
import com.zenred.zenredcomputing.domain.PostsDAO;

public class DomainTransferOperation {

	public static List<VisualizationCentricPostsResponse> operation(PostsDAO postsDAO, String emailAddress,
			String subject ) {

		List<Posts> notUsersPosts = postsDAO.readNonUserPostsWithinSubject(emailAddress, subject);
		List<Posts> usersPosts = postsDAO.readUserPostsWithinSubject(emailAddress, subject);

		DateOperation<Posts> dateOperation = new DateOperation<Posts>();
		List<Posts> combinedList = dateOperation.combineLists(notUsersPosts,
				usersPosts);

		List<VisualizationCentricPostsResponse> listVisualizationCentricPostsResponses = DomainTransfer
				.postsToPostsResponse(combinedList, subject);

		return listVisualizationCentricPostsResponses;
	}

}
