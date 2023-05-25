package ks46team02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import ks46team02.admin.interceptor.AdminInterceptor;
import ks46team02.company.interceptor.CompanyInterceptor;
import ks46team02.farm.interceptor.FarmInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Value("${files.path}")
	private String filePath;
	
	private final AdminInterceptor adminInterceptor;
	private final CompanyInterceptor companyInterceptor;
	private final FarmInterceptor farmInterceptor;
	
	
	public WebConfig(AdminInterceptor adminInterceptor, 
			CompanyInterceptor companyInterceptor,
			FarmInterceptor farmInterceptor) {
		this.adminInterceptor = adminInterceptor;
		this.companyInterceptor = companyInterceptor;
		this.farmInterceptor = farmInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/* add adminInterceptor */
		registry.addInterceptor(adminInterceptor)
			.addPathPatterns("/admin/**");

		/* add companyInterceptor */
		registry.addInterceptor(companyInterceptor)
			.addPathPatterns("/company/**")
			.excludePathPatterns("/**/addEmployee")
			.excludePathPatterns("/**/addCompanyIntro")
			.excludePathPatterns("/**/companyInfoUser")
			.excludePathPatterns("/**/companyInfo")
			.excludePathPatterns("/**/addCompany")
			.excludePathPatterns("/**/companyList")
			.excludePathPatterns("/**/modifyCompany")
			.excludePathPatterns("/**/companyTypeList")
			.excludePathPatterns("/**/companyEmployeeLevel")
			.excludePathPatterns("/**/companyProductCategory")
			.excludePathPatterns("/**/updateCompanyType")
			.excludePathPatterns("/**/removeCompany")
			.excludePathPatterns("/**/modifyCompanyProductCategory")
			.excludePathPatterns("/**/addCompanyProduct")
			.excludePathPatterns("/**/modifyProductName")
			.excludePathPatterns("/**/companyApproval")
			.excludePathPatterns("/**/removeProductCategory")
			.excludePathPatterns("/**/addContract");

		/* add farmInterceptor */
		
		registry.addInterceptor(farmInterceptor)
			.addPathPatterns("/farm/**")
			.excludePathPatterns("/**/farm/mentorMenteeContract")
			.excludePathPatterns("/**/mentorMenteeContractDetail");
		 }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("file:///c:" + filePath + "resources/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8088")
			.allowedOrigins("http://138.2.127.137");
		
		
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
}
