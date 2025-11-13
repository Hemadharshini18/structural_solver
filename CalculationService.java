package com.example.structuralsolver.service;

import com.example.structuralsolver.model.Load;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CalculationService {

    public static class ResultPoint {
        public double x, shear, moment;
        public ResultPoint(double x, double shear, double moment) {
            this.x = x; this.shear = shear; this.moment = moment;
        }
    }

    public List<ResultPoint> analyzeSimplySupported(double span, List<Load> loads, int steps){
        double total=0, M=0;

        for(Load l: loads){
            if(l.getType().equals("point")){
                total+=l.getMagnitude();
                M+=l.getMagnitude()*l.getPosition();
            } else {
                double w=l.getMagnitude();
                double load=w*span;
                total+=load;
                M+=load*(span/2);
            }
        }

        double Rb=M/span;
        double Ra=total-Rb;

        List<ResultPoint> list=new ArrayList<>();
        double dx=span/steps;

        for(int i=0;i<=steps;i++){
            double x=i*dx;
            double V=Ra;
            double Mx=Ra*x;

            for(Load l:loads){
                if(l.getType().equals("point") && l.getPosition()<=x){
                    V-=l.getMagnitude();
                    Mx-=l.getMagnitude()*(x-l.getPosition());
                } else if(l.getType().equals("udl")){
                    double w=l.getMagnitude();
                    double L=Math.min(x,span);
                    double load=w*L;
                    V-=load;
                    Mx-=load*(L/2);
                }
            }

            if(x>=span){
                V-=Rb;
                Mx-=Rb*(x-span);
            }

            list.add(new ResultPoint(x,V,Mx));
        }
        return list;
    }
}
