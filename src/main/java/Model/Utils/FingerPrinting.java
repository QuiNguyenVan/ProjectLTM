package Model.Utils;

import java.util.*;

public class FingerPrinting {

    private int k = 5;  // độ dài k-gram
    private int w = 4;  // window size

    public FingerPrinting() {}

    public FingerPrinting(int k, int w) {
        this.k = k;
        this.w = w;
    }

    // Chuẩn hóa văn bản (lowercase + bỏ dấu, bỏ ký tự đặc biệt)
    public String normalize(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-z0-9áàảãạăằắẳẵặâầấẩẫậèéẹẻẽêềếểễệìíĩỉịòóõỏọôồốổỗộơờớởỡợùúũụủưừứửữựỳýỷỹỵđ ]"," ")
                .replaceAll("\\s+"," ")
                .trim();
    }

    // Bước 1: Sinh k-grams
    public List<String> kgrams(String text) {
        List<String> grams = new ArrayList<>();
        for (int i = 0; i <= text.length() - k; i++) {
            grams.add(text.substring(i, i + k));
        }
        return grams;
    }

    // Bước 2: Hash k-gram
    public List<Integer> hashKgrams(List<String> grams) {
        List<Integer> hashes = new ArrayList<>();
        for (String g : grams) {
            hashes.add(g.hashCode()); 
        }
        return hashes;
    }

    // Bước 3: Winnowing (chọn fingerprint đặc trưng)
    public Set<Integer> winnowing(List<Integer> hashes) {
        Set<Integer> fingerprints = new HashSet<>();

        if (hashes.size() < w)
            return fingerprints;

        for (int i = 0; i <= hashes.size() - w; i++) {
            int minHash = Integer.MAX_VALUE;
            for (int j = i; j < i + w; j++) {
                minHash = Math.min(minHash, hashes.get(j));
            }
            fingerprints.add(minHash);
        }

        return fingerprints;
    }

    // Tính độ giống nhau bằng Jaccard
    public double similarity(Set<Integer> f1, Set<Integer> f2) {
        Set<Integer> intersection = new HashSet<>(f1);
        intersection.retainAll(f2);

        Set<Integer> union = new HashSet<>(f1);
        union.addAll(f2);

        if (union.size() == 0) return 0;

        return (double) intersection.size() / union.size();
    }

    // Full pipeline: Normalize → k-gram → hash → fingerprint
    public Set<Integer> fingerprints(String text) {
        text = normalize(text);
        return winnowing(
                hashKgrams(
                        kgrams(text)
                )
        );
    }
}
