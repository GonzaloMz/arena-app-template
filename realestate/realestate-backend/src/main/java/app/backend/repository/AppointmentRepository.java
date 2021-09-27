package app.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Appointment;
import app.backend.model.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment>{

	List<Appointment> findByStatusAndAppointmentDateBefore(AppointmentStatus status, Date date);
}
