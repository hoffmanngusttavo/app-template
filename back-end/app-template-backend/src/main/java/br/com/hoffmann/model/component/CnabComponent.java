package br.com.hoffmann.model.component;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CnabComponent {

    private final Path fileStorageLocation;
    private final JobLauncher jobLauncher;
    private final Job job;


    public CnabComponent(@Value("${file.upload-dir}") String fileUploadDir,
                         @Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
                         @Qualifier("importarArquivoCnabJob") Job job) {
        this.fileStorageLocation = Paths.get(fileUploadDir);
        this.jobLauncher = jobLauncher;
        this.job = job;
    }


    public void updloadCnabFile(MultipartFile file) throws Exception {
        Assert.notNull(file, "Arquivo não pode ser nulo");
        Assert.notNull(file.getOriginalFilename(), "Nome do arquivo não pode ser nulo");

        var fileName = StringUtils.cleanPath(file.getOriginalFilename());
        var targetLocation = fileStorageLocation.resolve(fileName);

        file.transferTo(targetLocation); //transferir para minha pasta tmp

        var parameters = new JobParametersBuilder()
                // regra de unicidade do job para executar só 1x o job
                .addJobParameter("cnab", file.getOriginalFilename(), String.class, true)
                .addJobParameter("cnabFile", "file:" + targetLocation, String.class, false)
                .toJobParameters();

        jobLauncher.run(job, parameters);

    }


    public void removerRegistrosTMP(){

        var pathNormalized = fileStorageLocation.toAbsolutePath().normalize();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathNormalized)) {
            for (Path path : directoryStream) {
                Files.delete(path);
            }
            System.out.println("All files deleted successfully!");
        } catch (IOException e) {
            System.err.println("Error deleting files: " + e.getMessage());
        }

    }



}
