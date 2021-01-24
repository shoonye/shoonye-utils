package shoonye.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class FileUtils {


		private static final int BUFFER_SIZE = 4096;



		public static void moveFile(File from, File to) throws IOException {

			// first just try to rename the file to a different location as this
			// is by far the fastest
			if (!from.renameTo(to)) {
				// we couldn't rename it, so go ahead and copy it instead
				copy(from, to);
				from.delete();
			}
		}

		public static void copy(File fileIn, File fileOut) throws IOException {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(
					fileIn));
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(fileOut));

			try {
				copy(in, out);
				fileOut.setLastModified(fileIn.lastModified());
			} finally {
				closeQuietly(in);
				closeQuietly(out);
			}
		}

		public static void copy(InputStream in, File fileOut) throws IOException {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(fileOut));

			try {
				copy(in, out);
			} finally {
				closeQuietly(out);
			}
		}

		public static void copy(InputStream in, OutputStream out)
				throws IOException {
			try {
				byte[] buffer = new byte[BUFFER_SIZE];
				int noBytes;

				while ((noBytes = in.read(buffer)) != -1) {
					out.write(buffer, 0, noBytes);
				}
				out.flush();
			} finally {
				closeQuietly(out);
			}
		}

		public static void closeQuietly(InputStream inputStream) {
			if (inputStream == null) {
				return;
			}

			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}

		public static void closeQuietly(OutputStream outputStream) {
			if (outputStream == null) {
				return;
			}

			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}

		public static void closeQuietly(Reader reader) {
			if (reader == null) {
				return;
			}

			try {
				reader.close();
			} catch (IOException e) {
			}
		}

		public static void closeQuietly(Writer writer) {
			if (writer == null) {
				return;
			}

			try {
				writer.close();
			} catch (IOException e) {
			}
		}

		public static String readFileToString(File file) throws IOException {
			return readFileToStringBuffer(file).toString();
		}

		public static StringBuffer readFileToStringBuffer(File file)
				throws IOException {
			BufferedReader in = new BufferedReader(new FileReader(file));

			StringBuffer sb = new StringBuffer();
			char[] chars = new char[BUFFER_SIZE];

			int i = 0;
			while ((i = in.read(chars)) != -1) {
				sb.append(chars, 0, i);
			}

			closeQuietly(in);

			return sb;
		}

		public static void moveFilesAndFolders(String fromPath, String toPath)
				throws IOException {
			String from = fixupLocation(fromPath);
			String to = fixupLocation(toPath);

			File files[] = new File(from).listFiles();

			for (int i = 0; i < files.length; i++) {

				File fromFile = files[i];
				File toFile = new File(to + fromFile.getName());

				if (!fromFile.renameTo(toFile)) {

					if (fromFile.isDirectory()) {
						moveFilesAndFolders(fromFile.getAbsolutePath(), toFile
								.getAbsolutePath());
					} else {
						toFile.mkdirs();
						toFile.delete();

						copy(fromFile, toFile);
					}

					fromFile.delete();
				}
			}
		}


		public static void deleteFilesAndFolders(String location) {
			File files[] = new File(location).listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteFilesAndFolders(files[i].getAbsolutePath());
				}

				files[i].delete();
			}
		}

		public static String getFileBase(File file) {

			// get just the filename, so we don't have to worry about
			// drive or path separators
			return getFileBase(file.getName());
		}

		public static String getFileBase(String fileName) {
			String name = getFileName(fileName);

			int i = name.lastIndexOf(".");

			if (i == -1) {
				return name;
			} else {
				return name.substring(0, i);
			}
		}

		public static String getFileExt(File file) {

			// get just the filename, so we don't have to worry about
			// drive or path separators
			return getFileExt(file.getName());
		}


		public static String getFileExt(String fileName) {
			String name = getFileName(fileName);

			int i = name.lastIndexOf(".");

			if (i == -1) {
				return "";
			} else {
				return name.substring(i);
			}
		}

		public static String getFileName(String fileName) {
			// loop backwards through the filename looking for the last drive or
			// directory separator
			for (int pos = fileName.length() - 1; pos >= 0; pos--) {

				// if this character is a drive or directory separator, then
				// we've reached the beginning of the filename
				if (":/\\".indexOf(fileName.charAt(pos)) >= 0) {
					return fileName.substring(pos + 1);
				}
			}

			return fileName;
		}

		public static String fixupLocation(String location) {

			// append the file-separator if the location doesn't already have it
			if (location != null) {

				// Also make sure that it's not already a root of some kind, we don't
				// want to turn an empty string into / or c: into c:/ which would make them
				// possibly destructive
				if (!location.equals("") && !location.endsWith(File.separator)
						&& !location.endsWith("/") && !location.endsWith("\\")
						&& !location.endsWith(":")) {

					location = location + File.separator;
				}
			}

			return location;
		}

		@SuppressWarnings("unchecked")
		public static void sortFileListOldestFirst(File[] fileArr) {
			// the default is OLDEST_FIRST order
			Arrays.sort(fileArr, new LastModifiedComparator());
		}

		@SuppressWarnings("unchecked")
		public static void sortFileListNewestFirst(File[] fileArr) {
			Arrays.sort(fileArr, new LastModifiedComparator(
					LastModifiedComparator.NEWEST_FIRST));
		}

		public static String unZiFile(File fileToZip) throws Exception {
			return unZiFile(fileToZip, true);
		}

		public static String zipFile(File fileToZip) throws Exception {
			return zipFile(fileToZip, true);
		}

		public static String unZiFile(String fileToZip) throws Exception {
			return unZiFile(fileToZip, false);
		}

		public static OutputStream unzipFile(String fileToUnzip) throws Exception {
			return unZipFile(fileToUnzip, false);
		}

		public static String zipFile(String fileToZip) throws Exception {
			return zipFile(fileToZip, false);
		}

		private static String unZiFile(Object fileToZip, boolean isFile)
				throws IOException {
			int buffer = 2048;
			ZipInputStream zis = null;
			StringBuffer decompressedFileName = new StringBuffer();
			final String ZIP_EXTENSION = ".zip";
			try {
				BufferedOutputStream dest;
				FileInputStream fis = null;
				if (isFile) {
					File fileToUnzip = (File) fileToZip;
					fis = new FileInputStream(fileToUnzip);
					decompressedFileName.append(fileToUnzip.getPath().replace(
							ZIP_EXTENSION, ""));
				} else {
					fis = new FileInputStream((String) fileToZip);
				}
				zis = new ZipInputStream(new BufferedInputStream(fis));
				ZipEntry entry;
				while ((entry = zis.getNextEntry()) != null) {
					String fileExtension = getFileExt(entry.getName());
					decompressedFileName.append(fileExtension);
					int count;
					byte data[] = new byte[buffer];

					// write the files to the disk
					FileOutputStream fos = new FileOutputStream(
							decompressedFileName.toString());
					dest = new BufferedOutputStream(fos, buffer);
					while ((count = zis.read(data, 0, buffer)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (zis != null) {
					zis.close();
				}
			}
			return decompressedFileName.toString();
		}

		private static OutputStream unZipFile(Object fileToZip, boolean isFile)
				throws Exception {
			int buffer = 2048;
			ZipInputStream zis = null;
			String decompressedFileName = null;
			final String ZIP_EXTENSION = ".zip";
			try {
				BufferedOutputStream dest;
				FileInputStream fis = null;
				if (isFile) {
					File fileToUnzip = (File) fileToZip;
					fis = new FileInputStream(fileToUnzip);
					decompressedFileName = fileToUnzip.getPath().replace(
							ZIP_EXTENSION, "");
				} else {
					fis = new FileInputStream((String) fileToZip);
				}
				zis = new ZipInputStream(new BufferedInputStream(fis));
				ZipEntry entry;
				while ((entry = zis.getNextEntry()) != null) {
					String fileExtension = getFileExt(entry.getName());
					decompressedFileName += fileExtension;
					int count;
					byte data[] = new byte[buffer];

					// write the files to the disk
					FileOutputStream fos = new FileOutputStream(
							decompressedFileName);
					dest = new BufferedOutputStream(fos, buffer);
					while ((count = zis.read(data, 0, buffer)) != -1) {
						dest.write(data, 0, count);
					}
					return dest;
				}
			} finally {
				if (zis != null) {
					zis.close();
				}
			}
			return null;
		}


		private static String zipFile(Object fileToZip, boolean isFile)
				throws Exception {
			int buffer = 2048;
			ZipOutputStream out = null;
			final String ZIP_EXTENSION = ".zip";
			try {
				BufferedInputStream origin = null;
				FileInputStream fis = null;
				String fileName = null;
				if (isFile) {
					File fileIn = (File) fileToZip;
					fis = new FileInputStream(fileIn);
					fileName = (fileIn.getPath());
				} else {
					fis = new FileInputStream((String) fileToZip);
					fileName = (String) fileToZip;
				}

				// constructs the destination
				File outFile = new File(fileName + ZIP_EXTENSION);

				// we create a new file if the zip file doesn't exist
				if (!outFile.exists()) {
					if (!outFile.createNewFile()) {
						throw new IOException("Error creating file : "
								+ outFile.getPath());
					}
				}

				byte data[] = new byte[buffer];
				FileOutputStream dest = new FileOutputStream(outFile);
				out = new ZipOutputStream(new BufferedOutputStream(dest));

				// get a list of files from current directory
				origin = new BufferedInputStream(fis, buffer);
				ZipEntry entry = new ZipEntry(getFileName(fileName));
				out.putNextEntry(entry);

				int count;
				while ((count = origin.read(data, 0, buffer)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();

				return fileName + ZIP_EXTENSION;
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		}

		public static String readFile(File file) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try {
				FileInputStream fis = new FileInputStream(file);
				byte buff[] = new byte[2000];
				int read = fis.read(buff);

				while (read > 0) {
					bout.write(buff, 0, read);
					read = fis.read(buff);
				}
				fis.close();
				return new String(bout.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public static void copyFile(File source, File dest) throws IOException {
			if (!dest.exists()) {
				dest.createNewFile();
			}
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(source);
				out = new FileOutputStream(dest);
				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
		}
		
		public static boolean removeDirectory(File directory) {
			if (directory == null)
				return false;
			if (!directory.exists())
				return true;
			if (!directory.isDirectory())
				return false;

			String[] list = directory.list();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File entry = new File(directory, list[i]);
					if (entry.isDirectory()) {
						if (!removeDirectory(entry))
							return false;
					} else {
						if (!entry.delete())
							return false;
					}
				}
			}

			return directory.delete();
		}
		
		
		public static String loadResourceAsString(String relativePath, String fileName) {
			if (fileName == null) {
				throw new IllegalArgumentException("fileName cannot be null");
			}
			StringBuilder fielpath = new StringBuilder();
			fielpath.append((relativePath != null) ? relativePath : "").append(fileName);
			InputStream inputStream = FileUtils.class.getResourceAsStream(fielpath.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				// LOG.error(e.getMessage());
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// LOG.error(e.getMessage());
				}
			}
			return sb.toString();
		}

		public static String loadResourceAsString(String fileName) {
			return loadResourceAsString(null, fileName);
		}
	}
