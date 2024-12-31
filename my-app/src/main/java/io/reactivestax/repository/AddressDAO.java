package io.reactivestax.repository;

import io.reactivestax.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class AddressDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    AddressDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public int createAddress(Address address){
        String sql = "INSERT INTO addresses (street, city, state, zipcode) VALUES (?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
         jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getState());
            ps.setString(4, address.getZipcode());
            return ps;
        }, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeyList().get(0); // Get the first key in the list
        int generatedId = (Integer) keyMap.get("id"); // Extract th

        log.info("Generated Address ID: {}", generatedId);
        return generatedId;
    }

    public List<Address> getAllAddress() {
        String sql = "SELECT * FROM addresses";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return Address.builder()
                    .id(rs.getInt("id"))
                    .street(rs.getString("street"))
                    .city(rs.getString("city"))
                    .state(rs.getString("state"))
                    .zipcode(rs.getString("zipcode"))
                    .build();
        });
    }

    public int updateAddress(Address address) {
        String sql = "UPDATE addresses SET street = ?, city = ?, state = ?, zipcode = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, address.getStreet(), address.getCity(), address.getState(),
                address.getZipcode(), address.getId());
        log.debug("Updated Address: {}", address);
        return result;
    }

    public int deleteAddress(int id) {
        String sql = "DELETE FROM addresses WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        log.debug("Deleted Address with ID: {}", id);
        return result;
    }

    public int createAddressWithNamedTemplate(Address address){
        String sql = "INSERT INTO addresses (street, city, state, zipcode) VALUES (:street, :city, :state, :zipcode)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("street", address.getStreet())
                .addValue("city", address.getCity())
                .addValue("state", address.getState())
                .addValue("zipcode", address.getZipcode());
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"} );
        int generatedId = keyHolder.getKey().intValue();
        log.debug("Generated Address ID: {}", generatedId);
        return generatedId;
    }

    public Address getAddressWithNamedTemplate(int id) {
        String sql = "SELECT * FROM addresses WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
          return Address.builder()
                   .id(rs.getInt("id"))
                   .street(rs.getString("street"))
                   .city(rs.getString("city"))
                   .state(rs.getString("state"))
                   .zipcode(rs.getString("zipcode"))
                   .build();
        });
    }
}
