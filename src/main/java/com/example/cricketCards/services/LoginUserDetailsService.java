package com.example.cricketCards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cricketCards.dao.RoomDao;
import com.example.cricketCards.models.Room;
import com.example.cricketCards.models.UserPrincipal;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RoomDao roomDao;

	@Override
	public UserDetails loadUserByUsername(String roomId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(roomId);
		Room room = roomDao.findRoom(roomId);
		
		return new UserPrincipal(room);
	}
}
