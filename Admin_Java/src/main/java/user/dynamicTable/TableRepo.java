package user.dynamicTable;

import org.springframework.data.jpa.repository.JpaRepository;

import user.authentication.UserDetails;

public interface TableRepo extends JpaRepository<TableDetails, Integer>{ 

}


