package com.project.sprintvotingapp.service;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.entity.Parameter;
import com.project.sprintvotingapp.entity.Sprint;
import com.project.sprintvotingapp.entity.User;
import com.project.sprintvotingapp.entity.Votes;
import com.project.sprintvotingapp.repository.ParameterRepository;
import com.project.sprintvotingapp.repository.SprintRepository;
import com.project.sprintvotingapp.repository.UserRepository;
import com.project.sprintvotingapp.repository.VoteRepository;
import com.project.sprintvotingapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private SprintRepository sprintRepository;
    @Transactional
    public APIResponse addVotes(HashMap parameterList , int sprintId){
        APIResponse apiResponse=new APIResponse();
        ArrayList<HashMap> listofVotes=new ArrayList<>();
        listofVotes= (ArrayList<HashMap>) parameterList.get("parameter_list");
        int vote_by= (int) parameterList.get("vote_by");

        for (HashMap voteData:listofVotes){
            voteData.put("sprint_id",sprintId);
            voteData.put("vote_by",vote_by);
            Votes vote= new Votes();
            User user_vote_by=userRepository.findById((int)voteData.get("vote_by"));

            User user_vote_to=userRepository.findById((int)voteData.get("vote_to"));
            if(user_vote_by==user_vote_to){
                apiResponse.setError("You cannot vote to yourself");
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());

                return apiResponse;

            }
            if(user_vote_to==null){
                apiResponse.setError("Vote_to Id Does not exist ");
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }

            Parameter parameter=parameterRepository.findByParameterId((int)voteData.get("parameter_id"));

            if(parameter==null){
                apiResponse.setError("Parameter does not exist");
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return apiResponse;

            }
            Sprint sprint=sprintRepository.findBySprintId((int)voteData.get("sprint_id"));
            if (sprint==null){
                apiResponse.setError("Sprint Does not exist");
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }
            List<Votes> list=  voteRepository.findByVoteBySprintIdAndParameterID(vote_by,parameter.getParameterId(),sprintId);

            if(list.size()>0){
                apiResponse.setError("User already Voted on the same Parameter");
                apiResponse.setData("Parameter= "+parameter.getParameterId());
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }

            vote.setVoteTo(user_vote_to);
            vote.setVoteBy(user_vote_by);
            vote.setSprintId(sprint);
            vote.setParameterId(parameter);
            voteRepository.save(vote);
        }
        apiResponse.setMessage("Voting Successful");
        apiResponse.setStatus(200);
        return apiResponse;
    }

    public APIResponse getVotes(int sprintId, int voteBy){
        APIResponse apiResponse=new APIResponse();
        List<Votes> listOfVotes=voteRepository.findBySprintIdAndVoteBy(sprintId,voteBy);
        System.out.println(listOfVotes);
        if(listOfVotes.size()==0){
            apiResponse.setError("User have not voted Yet");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Error getting data");
            return apiResponse;
        }
        ArrayList<HashMap<String, Integer>> voteList=new ArrayList<>();
        for (Votes v:listOfVotes){
            HashMap<String,Integer> voteData=new HashMap<>();
            voteData.put("parameter_id",v.getParameterId().getParameterId());
            voteData.put("vote_to",v.getVoteTo().getId());
            voteList.add(voteData);

        }
        HashMap<String, java.io.Serializable> voteDetail=new HashMap<String, java.io.Serializable>();
        voteDetail.put("vote_by",voteBy);
        voteDetail.put("sprint_id",sprintId);
        voteDetail.put("vote details",voteList);
        apiResponse.setData(voteDetail);
        apiResponse.setMessage("Your Vote Data is as follows");

        return apiResponse;

    }

    public APIResponse getResult(int sprintId){
        String winner = null;
        APIResponse apiResponse=new APIResponse();
        Sprint sprint=sprintRepository.findBySprintId(sprintId);
        if(sprint==null){
            apiResponse.setError("No sprint available with given ID");
            apiResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return apiResponse;
        }
        List<Votes> listOfVotes=voteRepository.findBySprintId(sprint);
        List<Object> voteDetailsList=new ArrayList<>();
        List<HashMap<String,Object>> listOfVoteData=new ArrayList<>();
        HashMap<String,Integer> numberOfVotes=new HashMap<>();
        Set<HashMap<String,Integer>> CounterList=new HashSet<>();
        final Utils.Counter<String> counts = new Utils.Counter<>();
        for(Votes vote :listOfVotes){
            //Assiging all vote details
           HashMap<String,Object> voteObj=new HashMap<>();
           voteObj.put("id" , vote.getVoteId());
           voteObj.put("vote_to",vote.getVoteTo().getUsername());
           voteObj.put("vote_by",vote.getVoteBy().getUsername());
           voteObj.put("parameter",vote.getParameterId().getParamaterName());
           listOfVoteData.add(voteObj);

           //finding Number of vote count
           counts.add(vote.getVoteTo().getUsername());
           numberOfVotes.put(vote.getVoteTo().getUsername(),counts.count(vote.getVoteTo().getUsername()));

        }

        CounterList.add(numberOfVotes);
        //finding winner

        int maxVotes=((int) Collections.max(numberOfVotes.values()));
        for (Map.Entry<String, Integer> entry : numberOfVotes.entrySet()) {
            if (entry.getValue() == maxVotes) {
                winner = entry.getKey();

            }
        }
        //Assigning Object to Main list
        HashMap<String,List<HashMap<String,Object>>> listOfVotesObj=new HashMap<>();
        HashMap<String,Set<HashMap<String,Integer>>> counterListObj=new HashMap<>();
        HashMap<String,String> winnerObj=new HashMap();
        listOfVotesObj.put("vote_details",listOfVoteData);
        counterListObj.put("Vote_counts",CounterList);
        winnerObj.put("Winner",winner);
        voteDetailsList.add(listOfVotesObj);
        voteDetailsList.add(counterListObj);
        voteDetailsList.add(winnerObj);
        apiResponse.setData(voteDetailsList);
        apiResponse.setMessage("Voting Result is as Follows");
        return apiResponse;

    }
}
